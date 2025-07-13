package com.bytescheduler.adminx.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleUtil {

    public static Locale getCurrentLocale() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return Locale.getDefault();
        }
        HttpServletRequest request = attrs.getRequest();
        return request.getLocale(); // 根据 Accept-Language 自动获取
    }
}
