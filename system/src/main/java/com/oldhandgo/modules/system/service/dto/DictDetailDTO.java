package com.oldhandgo.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
public class DictDetailDTO implements Serializable {

    private Long id;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private String sort;
}