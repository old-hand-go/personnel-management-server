package com.oldhandgo.logging.aspect;

import com.oldhandgo.common.utils.RequestHolder;
import com.oldhandgo.common.utils.SecurityUtils;
import com.oldhandgo.common.utils.StringUtils;
import com.oldhandgo.common.utils.ThrowableUtils;
import com.oldhandgo.logging.domain.Log;
import com.oldhandgo.logging.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author dormirr
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final LogService logService;

    private long currentTime = 0L;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.oldhandgo.logging.log.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime);
        logService.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()), joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR", System.currentTimeMillis() - currentTime);
        log.setExceptionDetail(ThrowableUtils.getStackTrace(e).getBytes());
        logService.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()), (ProceedingJoinPoint) joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        } catch (Exception e) {
            return "";
        }
    }
}