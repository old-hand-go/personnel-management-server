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
    private String phone;
    private String userName;
    private String passWord;
    private String address;
    private Long deptId;
    private Long jobId;
    private Byte isEnabled;
}
