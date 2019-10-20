package com.oldhandgo.system.modules.system.service.dto;

import com.oldhandgo.common.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 *
 * @author dormirr
 */
@Data
public class MenuQueryCriteria {

    /**
     * 多字段模糊
     */
    @Query(blurry = "name,path,component")
    private String blurry;
}