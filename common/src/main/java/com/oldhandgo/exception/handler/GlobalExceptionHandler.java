package com.oldhandgo.exception.handler;

import com.oldhandgo.exception.BadRequestException;
import com.oldhandgo.exception.EntityExistException;
import com.oldhandgo.exception.EntityNotFoundException;
import com.oldhandgo.utils.ThrowableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

/**
 * @author dormirr
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(), e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理接口无权限访问异常AccessDeniedException
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        ApiError apiError = new ApiError(FORBIDDEN.value(), e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        ApiError apiError = new ApiError(e.getStatus(), e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理实体已存在异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(), e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理实体不存在异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        ApiError apiError = new ApiError(NOT_FOUND.value(), e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        ApiError apiError = new ApiError(BAD_REQUEST.value(), str[1] + ":" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 统一返回API错误
     *
     * @param apiError API错误
     * @return 结果
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}