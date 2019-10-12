package com.oldhandgo.system.modules.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.sql.Timestamp;

@ToString
@Entity
@Data
public class Role {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String rolename;//角色名称
    private Long did;//所属部门
}
