package com.oldhandgo.system.modules.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "t_node", schema = "personnel-management-server")
@Data
public class Node {
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
    @Column(name = "url")
    private String url;//路径
    @Basic
    @Column(name = "ntext")
    private String ntext;//权限
}
