package com.oldhandgo.tools.service.dto;

import com.oldhandgo.common.annotation.Query;
import lombok.Data;

/**
 * @author dormirr
 */
@Data
public class LocalStorageQueryCriteria {

    /**
     * 模糊
     */
    @Query(blurry = "name,suffix,type,operate,size")
    private String blurry;
}