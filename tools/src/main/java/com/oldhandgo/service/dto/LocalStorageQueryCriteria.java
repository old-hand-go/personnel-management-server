package com.oldhandgo.service.dto;

import com.oldhandgo.annotation.Query;
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