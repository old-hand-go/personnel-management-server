package com.oldhandgo.system.modules.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.sql.Timestamp;

@ToString
@Entity
@Data
public class Node {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String url;//路径
    private String ntext;//权限
}
