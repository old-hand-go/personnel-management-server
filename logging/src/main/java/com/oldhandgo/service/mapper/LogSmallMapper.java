package com.oldhandgo.service.mapper;

import com.oldhandgo.domain.Log;
import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.service.dto.LogSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends EntityMapper<LogSmallDTO, Log> {

}