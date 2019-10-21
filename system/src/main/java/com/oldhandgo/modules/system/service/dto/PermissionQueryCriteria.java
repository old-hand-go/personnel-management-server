package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * @author dormirr
 */
@Data
public class PermissionQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,alias")
    private String blurry;
}
