package com.bytescheduler.adminx.common.entity;

import com.bytescheduler.adminx.enums.ErrorCode;
import com.bytescheduler.adminx.common.utils.LocaleUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    /** 暂时保留 */
    public static <T> Result<T> success() {
        return new Result<>(200, "成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(500, "失败", null);
    }

    public static <T> Result<T> failed(String msg) {
        return new Result<>(500, msg, null);
    }

    /** ✅ 成功 - 自动 locale，无 data */
    public static <T> Result<T> success(MessageSource messageSource) {
        return of(ErrorCode.SUCCESS, messageSource, null, LocaleUtil.getCurrentLocale());
    }

    /** ✅ 成功 - 自动 locale，有 data */
    public static <T> Result<T> success(T data, MessageSource messageSource) {
        return of(ErrorCode.SUCCESS, messageSource, data, LocaleUtil.getCurrentLocale());
    }

    /** ✅ 成功 - 指定 locale，有 data */
    public static <T> Result<T> success(T data, MessageSource messageSource, Locale locale) {
        return of(ErrorCode.SUCCESS, messageSource, data, locale);
    }

    /** ✅ 失败 - 自动 locale，无 data */
    public static <T> Result<T> failed(MessageSource messageSource) {
        return of(ErrorCode.FAIL, messageSource, null, LocaleUtil.getCurrentLocale());
    }

    /** ✅ 失败 - 指定 locale，无 data */
    public static <T> Result<T> failed(MessageSource messageSource, Locale locale) {
        return of(ErrorCode.FAIL, messageSource, null, locale);
    }

    /** ✅ 通用方法 - 自动 locale，有 data */
    public static <T> Result<T> of(ErrorCode errorCode, MessageSource messageSource, T data) {
        return of(errorCode, messageSource, data, LocaleUtil.getCurrentLocale());
    }

    /** ✅ 通用方法 - 自动 locale，无 data */
    public static <T> Result<T> of(ErrorCode errorCode, MessageSource messageSource) {
        return of(errorCode, messageSource, null, LocaleUtil.getCurrentLocale());
    }

    /** ✅ 通用方法 - 手动指定 locale */
    public static <T> Result<T> of(ErrorCode errorCode, MessageSource messageSource, T data, Locale locale) {
        String msg = messageSource.getMessage(errorCode.getMessageKey(), null, locale);
        return new Result<>(errorCode.getCode(), msg, data);
    }
}
