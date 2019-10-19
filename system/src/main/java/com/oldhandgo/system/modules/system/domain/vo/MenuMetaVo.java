package com.oldhandgo.system.modules.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {

    private String menuName;

    private Byte isCache;
}
