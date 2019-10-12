package com.oldhandgo.system.modules.system.domain;


import lombok.Data;
import java.sql.Timestamp;


@Data
public class Ur {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long uid;
    private Long rid;
}
