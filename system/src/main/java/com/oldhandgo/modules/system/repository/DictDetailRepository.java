package com.oldhandgo.modules.system.repository;

import com.oldhandgo.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dormirr
 */
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor {

}