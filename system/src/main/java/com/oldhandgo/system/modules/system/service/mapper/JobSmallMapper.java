package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.common.mapper.EntityMapper;
import com.oldhandgo.system.modules.system.domain.Job;
import com.oldhandgo.system.modules.system.service.dto.JobSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends EntityMapper<JobSmallDTO, Job> {

}