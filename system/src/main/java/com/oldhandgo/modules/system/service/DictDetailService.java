package com.oldhandgo.modules.system.service;

import com.oldhandgo.modules.system.domain.DictDetail;
import com.oldhandgo.modules.system.service.dto.DictDetailDTO;
import com.oldhandgo.modules.system.service.dto.DictDetailQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author dormirr
 */
@CacheConfig(cacheNames = "dictDetail")
public interface DictDetailService {

    /**
     * findById
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDetailDTO findById(Long id);

    /**
     * create
     *
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDetailDTO create(DictDetail resources);

    /**
     * update
     *
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(DictDetail resources);

    /**
     * delete
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    @Cacheable
    Map queryAll(DictDetailQueryCriteria criteria, Pageable pageable);
}