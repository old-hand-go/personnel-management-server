package com.oldhandgo.service.mapper;

import com.oldhandgo.domain.Log;
import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.service.dto.LogErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends EntityMapper<LogErrorDTO, Log> {

}