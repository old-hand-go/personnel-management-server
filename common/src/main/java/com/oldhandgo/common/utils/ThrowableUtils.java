package com.oldhandgo.common.utils;

import com.oldhandgo.common.exception.BadRequestException;
import org.hibernate.exception.ConstraintViolationException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具
 *
 * @author dormirr
 */
public class ThrowableUtils {

    /**
     * 获取堆栈信息
     *
     * @param throwable 异常
     * @return 信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    /**
     * 引发外键异常
     *
     * @param e   异常
     * @param msg 信息
     */
    public static void throwForeignKeyException(Throwable e, String msg) {
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new BadRequestException(msg);
        }
        assert false;
        throw new BadRequestException("删除失败：" + t.getMessage());
    }
}
