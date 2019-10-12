package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List findByUid(String uid);//查询节点

}
