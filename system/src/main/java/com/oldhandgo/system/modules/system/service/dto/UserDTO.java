package com.oldhandgo.system.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dormir
 */
@Data
public class UserDTO implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String email;
    private String userName;
    private Timestamp entryTime;
    private String phone;
    private String address;
    private String passWord;
    private Timestamp lastPasswordResetTime;
    private Long deptId;
    private Long jobId;
    private Byte isEnabled;
}
