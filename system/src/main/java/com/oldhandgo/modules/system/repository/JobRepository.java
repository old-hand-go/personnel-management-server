package com.oldhandgo.modules.system.repository;

import com.oldhandgo.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dormirr
 */
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {

}