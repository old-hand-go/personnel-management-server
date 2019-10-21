package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dormirr
 */
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {

}