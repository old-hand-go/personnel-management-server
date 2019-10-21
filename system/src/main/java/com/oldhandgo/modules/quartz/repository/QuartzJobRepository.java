package com.oldhandgo.modules.quartz.repository;

import com.oldhandgo.modules.quartz.domain.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author dormirr
 */
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Long>, JpaSpecificationExecutor {

    /**
     * 查询启用的任务
     *
     * @return 结果
     */
    List<QuartzJob> findByIsPauseIsFalse();
}