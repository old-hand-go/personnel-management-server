package com.oldhandgo.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
public class DeptSmallDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}