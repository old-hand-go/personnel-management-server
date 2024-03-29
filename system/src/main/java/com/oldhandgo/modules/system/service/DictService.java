package com.oldhandgo.modules.system.service;

import com.oldhandgo.modules.system.domain.Dict;
import com.oldhandgo.modules.system.service.dto.DictDTO;
import com.oldhandgo.modules.system.service.dto.DictQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author dormirr
 */
@CacheConfig(cacheNames = "dict")
public interface DictService {

    /**
     * 查询
     *
     * @param dict
     * @param pageable
     * @return
     */
    @Cacheable
    Object queryAll(DictQueryCriteria dict, Pageable pageable);

    /**
     * findById
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDTO findById(Long id);

    /**
     * create
     *
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDTO create(Dict resources);

    /**
     * update
     *
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dict resources);

    /**
     * delete
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}