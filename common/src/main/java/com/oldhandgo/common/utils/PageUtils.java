package com.oldhandgo.common.utils;

import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 分页工具
 *
 * @author dormirr
 */
public class PageUtils extends cn.hutool.core.util.PageUtil {

    /**
     * 分页
     *
     * @param page 页数
     * @param size 大小
     * @param list 内容
     * @return 分页
     */
    public static List toPage(int page, int size, List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;

        if (fromIndex > list.size()) {
            return new ArrayList();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }

    /**
     * @param object        内容
     * @param totalElements 总内容
     * @return map
     */
    public static Map toPage(Object object, Object totalElements) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", object);
        map.put("totalElements", totalElements);

        return map;
    }

    /**
     * Page 数据处理，预防redis反序列化报错
     *
     * @param page 数据
     * @return 处理结果
     */
    public static Map toPage(Page page) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        return map;
    }
}
