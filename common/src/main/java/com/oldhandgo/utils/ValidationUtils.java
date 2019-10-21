package com.oldhandgo.utils;

import com.oldhandgo.exception.BadRequestException;

import java.util.Optional;

/**
 * @author dormir
 */
public class ValidationUtils {
    /**
     * 判断邮箱是否合法
     *
     * @param string 邮箱
     * @return 判断结果
     */
    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }

    /**
     * 验证空
     *
     * @param optional  实体类
     * @param entity    实体类名
     * @param parameter 类
     * @param value     值
     */
    public static void isNull(Optional optional, String entity, String parameter, Object value) {
        if (optional.isEmpty()) {
            String msg = entity
                    + " 不存在 "
                    + "{ " + parameter + ":" + value.toString() + " }";
            throw new BadRequestException(msg);
        }
    }
}