package com.oldhandgo.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.oldhandgo.domain.Picture;
import com.oldhandgo.exception.BadRequestException;
import com.oldhandgo.repository.PictureRepository;
import com.oldhandgo.service.PictureService;
import com.oldhandgo.service.dto.PictureQueryCriteria;
import com.oldhandgo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author dormirr
 */
@Slf4j
@Service(value = "pictureService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PictureServiceImpl implements PictureService {

    private static final String SUCCESS = "success";
    private static final String CODE = "code";
    private static final String MSG = "message";
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Object queryAll(PictureQueryCriteria criteria, Pageable pageable) {
        return PageUtils.toPage(pictureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Picture upload(MultipartFile multipartFile, String username) {
        File file = FileUtils.toFile(multipartFile);
        HashMap<String, Object> paramMap = new HashMap<>(1);

        paramMap.put("smfile", file);
        String result = HttpUtil.post(ElAdminConstant.Url.SM_MS_URL, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        Picture picture;
        if (!jsonObject.get(CODE).toString().equals(SUCCESS)) {
            throw new BadRequestException(TranslatorUtils.translate(jsonObject.get(MSG).toString()));
        }
        //转成实体类
        picture = JSON.parseObject(jsonObject.get("data").toString(), Picture.class);
        picture.setSize(FileUtils.getSize(Integer.parseInt(picture.getSize())));
        picture.setUsername(username);
        picture.setFilename(FileUtils.getFileNameNoEx(multipartFile.getOriginalFilename()) + "." + FileUtils.getExtensionName(multipartFile.getOriginalFilename()));
        pictureRepository.save(picture);
        //删除临时文件
        FileUtils.del(file);
        return picture;

    }

    @Override
    public Picture findById(Long id) {
        Optional<Picture> picture = pictureRepository.findById(id);
        ValidationUtils.isNull(picture, "Picture", "id", id);
        return picture.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Picture picture) {
        try {
            String result = HttpUtil.get(picture.getDelete());
            pictureRepository.delete(picture);
        } catch (Exception e) {
            pictureRepository.delete(picture);
        }

    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            delete(findById(id));
        }
    }
}