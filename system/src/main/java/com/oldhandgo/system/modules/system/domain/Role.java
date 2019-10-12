package com.oldhandgo.system.modules.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "t_role", schema = "personnel-management-server")
@Data
public class Role {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Basic
    @Column(name = "rolename")
    private String rolename;//角色名称
    @Basic
    @Column(name = "did")
    private Long did;//所属部门
}
