package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 *
 * @author dormirr
 */
@Data
public class DictQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,remark")
    private String blurry;
}