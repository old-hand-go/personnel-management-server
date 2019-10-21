package com.oldhandgo.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * @author dormirr
 */
@Data
public class LogQueryCriteria {

    /**
     * 多字段模糊
     */
    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;
}