package com.oldhandgo.logging.service;

import com.oldhandgo.logging.domain.Log;
import com.oldhandgo.logging.service.dto.LogQueryCriteria;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

/**
 * @author dormirr
 */
public interface LogService {

    /**
     * 查询全部日志
     *
     * @param criteria 条件
     * @param pageable 分页
     * @return 结果
     */
    Object queryAll(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 根据用户查询全部日志
     *
     * @param criteria 条件
     * @param pageable 分页
     * @return 结果
     */
    Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 新增日志
     *
     * @param username  用户名
     * @param ip        IP
     * @param joinPoint joinPoint
     * @param log       日志
     */
    @Async
    void save(String username, String ip, ProceedingJoinPoint joinPoint, Log log);

    /**
     * 查询异常详情
     *
     * @param id 主键
     * @return 结果
     */
    Object findByErrDetail(Long id);
}