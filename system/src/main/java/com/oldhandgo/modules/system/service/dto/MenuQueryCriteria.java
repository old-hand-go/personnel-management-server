package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 */
@Data
public class MenuQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,path,component")
    private String blurry;
}
