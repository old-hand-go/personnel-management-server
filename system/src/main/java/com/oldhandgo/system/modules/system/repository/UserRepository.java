package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author dormir
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 查询姓名为userName的所有人
     *
     * @param userName 姓名
     * @return 查询结果
     */
    List<User> findByUserName(String userName);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 查询结果
     */
    User findByEmail(String email);
}
