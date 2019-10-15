package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * @author dormirr
 */
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    /**
     * 根据邮箱查询
     *
     * @param email     邮箱
     * @param isEnabled 开启的用户 必为1
     * @return 查询结果
     */
    Optional<User> findByEmailAndIsEnabled(String email, Byte isEnabled);
}
