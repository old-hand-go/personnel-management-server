package com.oldhandgo.system.modules.quartz.service.dto;

import com.oldhandgo.common.annotation.Query;
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