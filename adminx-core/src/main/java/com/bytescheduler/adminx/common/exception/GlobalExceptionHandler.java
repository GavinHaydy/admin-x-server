package com.bytescheduler.adminx.common.exception;

import com.bytescheduler.adminx.common.entity.Result;
import com.bytescheduler.adminx.common.utils.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        Locale locale = LocaleUtil.getCurrentLocale();
        String message;

        if (e.getMessageKey() != null) {
            message = messageSource.getMessage(e.getMessageKey(), null, locale);
        } else {
            message = e.getMessage();
        }

        return new Result<>(e.getCode(), message, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidation(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new Result<>(400, errorMsg, null);
    }

    @ExceptionHandler(DataAccessException.class)
    public Result<?> handleDataAccess(DataAccessException e) {
        return new Result<>(500, "数据库操作失败: " + e.getMostSpecificCause().getMessage(), null);
    }

    @ExceptionHandler(SQLException.class)
    public Result<?> handleSql(SQLException e) {
        return new Result<>(500, "数据库执行错误: " + e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        return new Result<>(500, e.getMessage(), null);
    }
}
