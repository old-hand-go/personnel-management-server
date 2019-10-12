package com.oldhandgo.system.modules.system.domain;


import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;


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
