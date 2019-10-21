package com.oldhandgo.modules.system.service.mapper;

import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.modules.system.domain.Job;
import com.oldhandgo.modules.system.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<JobDTO, Job> {

    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDTO toDto(Job job, String deptSuperiorName);
}