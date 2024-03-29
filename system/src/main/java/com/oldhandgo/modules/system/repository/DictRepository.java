package com.oldhandgo.modules.system.repository;

import com.oldhandgo.modules.system.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dormirr
 */
public interface DictRepository extends JpaRepository<Dict, Long>, JpaSpecificationExecutor {

}