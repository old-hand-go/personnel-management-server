package com.oldhandgo.system.modules.system.service.dto;

import com.oldhandgo.common.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 *
 * @author dormirr
 */
@Data
public class PermissionQueryCriteria {

    /**
     * 多字段模糊
     */
    @Query(blurry = "name,alias")
    private String blurry;
}