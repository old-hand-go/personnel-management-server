package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author dormirr
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor {

    /**
     * findByName
     *
     * @param name
     * @return
     */
    Menu findByName(String name);

    /**
     * findByName
     *
     * @param name
     * @return
     */
    Menu findByComponentName(String name);

    /**
     * findByPid
     *
     * @param pid
     * @return
     */
    List<Menu> findByPid(long pid);

    LinkedHashSet<Menu> findByRoles_IdOrderBySortAsc(Long id);
}