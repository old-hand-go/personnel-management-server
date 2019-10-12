package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author dormir
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 查询结果
     */
    User findByEmail(String email);

    List findByUid(String uid);//查询节点
}
