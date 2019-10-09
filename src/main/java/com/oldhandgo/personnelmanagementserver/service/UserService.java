package com.oldhandgo.personnelmanagementserver.service;

import com.oldhandgo.personnelmanagementserver.service.dto.UserDTO;

/**
 * @author dormir
 */
public interface UserService {
    /**
     * 根据ID查询用户
     *
     * @param id
     * @return 查询结果
     */
    UserDTO findById(long id);

    /**
     * 根据用户姓名查询用户
     *
     * @param userName 用户姓名
     * @return 查询结果
     */
    UserDTO findByUserName(String userName) throws Exception;
}
