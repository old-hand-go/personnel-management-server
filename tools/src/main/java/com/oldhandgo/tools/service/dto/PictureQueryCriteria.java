package com.oldhandgo.tools.service.dto;

import com.oldhandgo.common.annotation.Query;
import lombok.Data;

/**
 * 图床
 *
 * @author dormirr
 */
@Data
public class PictureQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String filename;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}