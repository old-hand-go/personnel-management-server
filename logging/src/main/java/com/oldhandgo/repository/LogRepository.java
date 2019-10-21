package com.oldhandgo.repository;

import com.oldhandgo.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author dormirr
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor {

    /**
     * 获取一个时间段的IP记录
     *
     * @param date1 开始时间
     * @param date2 结束时间
     * @return IP记录
     */
    @Query(value = "select count(*) FROM (select request_ip FROM log where create_time between ?1 and ?2 GROUP BY request_ip) as s", nativeQuery = true)
    Long findIp(String date1, String date2);

    /**
     * findExceptionById
     *
     * @param id 主键
     * @return 异常
     */
    @Query(value = "select exception_detail FROM log where id = ?1", nativeQuery = true)
    String findExceptionById(Long id);
}