package com.oldhandgo.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 异常实体
 *
 * @author dormirr
 */
@Data
class ApiError {

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(Integer status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}