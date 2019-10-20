package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Menu;
import com.oldhandgo.system.modules.system.domain.RolesMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author dormirr
 */
public interface RolesMenusRepository extends JpaRepository<RolesMenus, Long>, JpaSpecificationExecutor {

}
