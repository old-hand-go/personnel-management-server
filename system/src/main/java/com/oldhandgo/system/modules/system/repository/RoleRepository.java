package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


/**
 * @author dormirr
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor {

    /**
     * 根据角色名字查询角色
     *
     * @param roleName 角色名字
     * @return 角色
     */
    Role findByRoleName(String roleName);

    /**
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

    /**
     * 删除角色对应的权限
     *
     * @param id 权限ID
     */
    @Modifying
    @Query(value = "delete from roles_permissions where permission_id = ?1", nativeQuery = true)
    void untiedPermission(Long id);

    /**
     * 删除角色对应的菜单权限
     *
     * @param id 菜单ID
     */
    @Modifying
    @Query(value = "delete from roles_menus where menu_id = ?1", nativeQuery = true)
    void untiedMenu(Long id);
}
