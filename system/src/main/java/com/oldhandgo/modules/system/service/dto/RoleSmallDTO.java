package com.oldhandgo.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
public class RoleSmallDTO implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
