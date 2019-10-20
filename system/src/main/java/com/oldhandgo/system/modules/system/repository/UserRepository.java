package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

/**
 * @author dormirr
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    /**
     * 根据邮箱查询
     *
     * @param email     邮箱
     * @param isEnabled 开启的用户 必为1
     * @return 查询结果
     */
    Optional<User> findByEmailAndIsEnabled(String email, Byte isEnabled);

    /**
     * findByUsername
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 修改密码
     *
     * @param username
     * @param pass
     * @param lastPasswordResetTime
     */
    @Modifying
    @Query(value = "update personnel_management_server.user set pass_word = ?2 , update_time = ?3 where email = ?1", nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改头像
     *
     * @param username
     * @param url
     */
    @Modifying
    @Query(value = "update personnel_management_server.user set  avatar= ?2 where user_name = ?1", nativeQuery = true)
    void updateAvatar(String username, String url);

    /**
     * 修改邮箱
     *
     * @param username
     * @param email
     */
    @Modifying
    @Query(value = "update personnel_management_server.user set email = ?2 where user_name = ?1", nativeQuery = true)
    void updateEmail(String username, String email);
}
