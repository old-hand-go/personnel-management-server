package com.oldhandgo.modules.system.service.mapper;

import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.modules.system.domain.Menu;
import com.oldhandgo.modules.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

}
