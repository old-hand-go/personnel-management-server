package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * @author dormirr
 */
@Data
public class RoleQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,remark")
    private String blurry;
}
