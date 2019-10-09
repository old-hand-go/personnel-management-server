package com.oldhandgo.system.modules.system.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dormir
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private Long id;
    private String createTime;
    private String updateTime;
    private String userName;
    private String address;
    private String email;
    private String position;
    private String entryTime;
}
