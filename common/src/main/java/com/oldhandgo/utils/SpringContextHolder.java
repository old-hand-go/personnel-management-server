package com.oldhandgo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring上下文持有者
 *
 * @author dormirr
 */
@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的应用上下文
     *
     * @return 应用上下文
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量应用上下文中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param name Bean的名字
     * @param <T>  静态变量类型
     * @return 转换后的类型
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量应用上下文中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param requiredType Bean名字
     * @param <T>          静态变量类型
     * @return 转换后的类型
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 检查应用上下文不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }
    }

    /**
     * 清除Spring上下文持有者中的应用上下文为Null.
     */
    public static void clearHolder() {
        log.debug("清除SpringContextHolder中的ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }

    /**
     * 删除Spring上下文持有者
     */
    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }

    /**
     * 设置Spring上下文持有者
     *
     * @param applicationContext Spring上下文持有者
     * @throws BeansException 异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null) {
            log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                    + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;
    }
}
