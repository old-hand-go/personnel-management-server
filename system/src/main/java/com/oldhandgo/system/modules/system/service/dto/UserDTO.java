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
    private String userName;
    private String address;
    private String email;
    private String position;
    private Timestamp entryTime;
}
