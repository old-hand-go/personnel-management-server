package com.oldhandgo.system.modules.system.service;

import com.oldhandgo.system.modules.system.service.dto.UserDTO;

/**
 * @author dormir
 */
public interface UserService {

    /**
     * 根据邮箱查询
     *
     * @param email     邮箱
     * @param isEnabled 开启的用户 必为1
     * @return 查询结果
     */
    UserDTO findByEmailAndIsEnabled(String email, Byte isEnabled);

}
