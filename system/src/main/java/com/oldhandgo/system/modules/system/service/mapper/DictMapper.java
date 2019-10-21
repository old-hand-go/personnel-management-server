package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.common.mapper.EntityMapper;
import com.oldhandgo.system.modules.system.domain.Dict;
import com.oldhandgo.system.modules.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends EntityMapper<DictDTO, Dict> {

}