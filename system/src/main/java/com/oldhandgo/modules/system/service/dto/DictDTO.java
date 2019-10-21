package com.oldhandgo.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
public class DictDTO implements Serializable {

    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;
}
