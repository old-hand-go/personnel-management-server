package com.oldhandgo.personnelmanagementserver.utils;

/**
 * @author dormir
 */
public class ValidationUtil {
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
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }
}
