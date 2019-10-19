package com.oldhandgo.tools.service.impl;

import com.oldhandgo.common.utils.*;
import com.oldhandgo.tools.domain.LocalStorage;
import com.oldhandgo.tools.repository.LocalStorageRepository;
import com.oldhandgo.tools.service.LocalStorageService;
import com.oldhandgo.tools.service.dto.LocalStorageDTO;
import com.oldhandgo.tools.service.dto.LocalStorageQueryCriteria;
import com.oldhandgo.tools.service.mapper.LocalStorageMapper;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

/**
 * @author dormirr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LocalStorageServiceImpl implements LocalStorageService {

    private final LocalStorageRepository localStorageRepository;
    private final LocalStorageMapper localStorageMapper;

    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    public LocalStorageServiceImpl(LocalStorageRepository localStorageRepository, LocalStorageMapper localStorageMapper) {
        this.localStorageRepository = localStorageRepository;
        this.localStorageMapper = localStorageMapper;
    }

    @Override
    public Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable) {
        Page<LocalStorage> page = localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtils.toPage(page.map(localStorageMapper::toDto));
    }

    @Override
    public Object queryAll(LocalStorageQueryCriteria criteria) {
        return localStorageMapper.toDto(localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public LocalStorageDTO findById(Long id) {
        Optional<LocalStorage> localStorage = localStorageRepository.findById(id);
        ValidationUtils.isNull(localStorage, "LocalStorage", "id", id);
        return localStorageMapper.toDto(localStorage.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LocalStorageDTO create(String name, MultipartFile multipartFile) {
        FileUtils.checkSize(maxSize, multipartFile.getSize());
        String suffix = FileUtils.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtils.getFileType(suffix);
        File file = FileUtils.upload(multipartFile, path + type + File.separator);
        try {
            name = StringUtils.isBlank(name) ? FileUtils.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    file.getName(),
                    name,
                    suffix,
                    file.getPath(),
                    type,
                    FileUtils.getSize(multipartFile.getSize()),
                    SecurityUtils.getUserName()
            );
            return localStorageMapper.toDto(localStorageRepository.save(localStorage));
        } catch (Exception e) {
            FileUtils.del(file);
            throw e;
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Jie\\Pictures\\Saved Pictures\\demo1.jpg");
        System.out.println(FileUtils.getType(file));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LocalStorage resources) {
        Optional<LocalStorage> optionalLocalStorage = localStorageRepository.findById(resources.getId());
        ValidationUtils.isNull(optionalLocalStorage, "LocalStorage", "id", resources.getId());
        LocalStorage localStorage = optionalLocalStorage.get();
        localStorage.copy(resources);
        localStorageRepository.save(localStorage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        LocalStorage storage = localStorageRepository.findById(id).get();
        FileUtils.del(storage.getFilePath());
        localStorageRepository.delete(storage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LocalStorage storage = localStorageRepository.findById(id).get();
            FileUtils.del(storage.getFilePath());
            localStorageRepository.delete(storage);
        }
    }
}