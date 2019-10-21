package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
 * @author dormirr
 */
@Data
public class DeptQueryCriteria {

    @Query(type = Query.Type.IN, propName = "id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;
}