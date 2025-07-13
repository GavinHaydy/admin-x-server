package com.bytescheduler.adminx.common.exception;

import com.bytescheduler.adminx.enums.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;
    private final String messageKey;

    /** 仅自定义 message（默认 code = 500） */
    public BusinessException(String message) {
        super(message);
        this.code = 500;
        this.messageKey = null;
    }

    /** 自定义 code + message */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.messageKey = null;
    }

    /** 使用 ErrorCode 枚举构造（推荐） */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.name());
        this.code = errorCode.getCode();
        this.messageKey = errorCode.getMessageKey();
    }
}

