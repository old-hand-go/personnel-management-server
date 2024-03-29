package com.oldhandgo.modules.system.service.dto;

import com.oldhandgo.annotation.Query;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author dormirr
 */
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;
}