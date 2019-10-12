package com.oldhandgo.system.modules.system.domain;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.sql.Timestamp;

@ToString
@Entity
@Data
public class Rn {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long rid;
    private Long nid;
}
