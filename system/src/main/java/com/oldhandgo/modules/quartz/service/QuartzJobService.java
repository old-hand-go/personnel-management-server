package com.oldhandgo.modules.quartz.service;

import com.oldhandgo.modules.quartz.domain.QuartzJob;
import com.oldhandgo.modules.quartz.service.dto.JobQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author dormirr
 */
@CacheConfig(cacheNames = "quartzJob")
public interface QuartzJobService {

    /**
     * 查询全部
     *
     * @param criteria 职位查询条件
     * @param pageable 分页
     * @return 查询结果
     */
    @Cacheable
    Object queryAll(JobQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部日志
     *
     * @param criteria 职位查询条件
     * @param pageable 分页
     * @return 结果
     */
    Object queryAllLog(JobQueryCriteria criteria, Pageable pageable);

    /**
     * 创建任务
     *
     * @param resources 属性
     * @return 结果
     */
    @CacheEvict(allEntries = true)
    QuartzJob create(QuartzJob resources);

    /**
     * 更新
     *
     * @param resources 属性
     */
    @CacheEvict(allEntries = true)
    void update(QuartzJob resources);

    /**
     * 删除
     *
     * @param quartzJob 计划任务
     */
    @CacheEvict(allEntries = true)
    void delete(QuartzJob quartzJob);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 结果
     */
    @Cacheable(key = "#p0")
    QuartzJob findById(Long id);

    /**
     * 更改定时任务状态
     *
     * @param quartzJob 计划任务
     */
    @CacheEvict(allEntries = true)
    void updateIsPause(QuartzJob quartzJob);

    /**
     * 立即执行定时任务
     *
     * @param quartzJob 计划任务
     */
    void execution(QuartzJob quartzJob);
}