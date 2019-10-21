package com.oldhandgo.service.mapper;

import com.oldhandgo.domain.LocalStorage;
import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.service.dto.LocalStorageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends EntityMapper<LocalStorageDTO, LocalStorage> {

}