package com.oldhandgo.system.modules.system.service;

import com.oldhandgo.system.modules.system.service.dto.UserDTO;

import java.util.List;

/**
 * @author dormir
 */
public interface UserService {

    /**
     * 根据ID查询用户
     *
     * @param id 用户id
     * @return 查询结果
     */
    UserDTO findById(long id);

    /**
     * 根据用户邮箱查询用户
     *
     * @param email 用户邮箱
     * @return 查询结果
     */
    UserDTO findByEmail(String email);

    List findByUid(String uid);
}
