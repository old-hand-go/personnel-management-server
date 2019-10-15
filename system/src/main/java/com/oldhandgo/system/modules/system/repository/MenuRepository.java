package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author dormirr
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, PagingAndSortingRepository<Menu, Long> {

    /**
     * 根据菜单名称查询菜单
     *
     * @param menuName 菜单名称
     * @return 菜单
     */
    Optional<Menu> findByMenuName(String menuName);


    /**
     * 根据部门名称查询菜单
     *
     * @param componentName 部门名称
     * @return 菜单
     */
    Optional<Menu> findByComponentName(String componentName);

    /**
     * 根据上级菜单查询所属菜单
     *
     * @param pid 上级菜单ID
     * @return 所属菜单
     */
    List<Menu> findAllByPidOrderBySortAsc(Long pid);
}
