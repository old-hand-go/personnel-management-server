package com.oldhandgo.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
@NoArgsConstructor
public class JobSmallDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}