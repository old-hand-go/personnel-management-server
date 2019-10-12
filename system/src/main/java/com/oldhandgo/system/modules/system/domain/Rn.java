package com.oldhandgo.system.modules.system.domain;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "t_rn", schema = "personnel-management-server")
@Data
public class Rn {
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
    @Column(name = "rid")
    private Long rid;
    @Basic
    @Column(name = "nid")
    private Long nid;
}
