package com.oldhandgo.logging.service.mapper;

import com.oldhandgo.common.mapper.EntityMapper;
import com.oldhandgo.logging.domain.Log;
import com.oldhandgo.logging.service.dto.LogErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends EntityMapper<LogErrorDTO, Log> {

}