package com.oldhandgo.tools.service.mapper;

import com.oldhandgo.common.mapper.EntityMapper;
import com.oldhandgo.tools.domain.LocalStorage;
import com.oldhandgo.tools.service.dto.LocalStorageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends EntityMapper<LocalStorageDTO, LocalStorage> {

}