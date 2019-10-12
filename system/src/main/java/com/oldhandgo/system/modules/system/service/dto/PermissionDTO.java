package com.oldhandgo.system.modules.system.service.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PermissionDTO {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String permissionName;
    private String alias;
    private Long pid;
}
