package com.oldhandgo.modules.quartz.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

/**
 * @author dormirr
 */
@Data
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;
}