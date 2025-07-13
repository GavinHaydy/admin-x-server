package com.bytescheduler.adminx.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(200,"success.success"),
    FAIL(400,"error.fail"),
    CAPTCHA_NOTFOUND(10001,"error.captcha_not_found");




    private final int code;
    private final String messageKey;

    ErrorCode(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }
}
