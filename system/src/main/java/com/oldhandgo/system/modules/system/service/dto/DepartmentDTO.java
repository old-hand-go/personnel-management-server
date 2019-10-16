package com.oldhandgo.system.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dormirr
 */
@Data
public class DepartmentDTO implements Serializable {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String departmentName;
    private Long pid;
    private Byte enabled;
}
