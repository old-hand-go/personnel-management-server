package com.oldhandgo.modules.system.service.mapper;

import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.modules.system.domain.Job;
import com.oldhandgo.modules.system.service.dto.JobSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends EntityMapper<JobSmallDTO, Job> {

}