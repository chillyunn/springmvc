package com.jirandata.util;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static String getClientIP(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (Header header: Header.values()){
            String clientIpFromHeader=request.getHeader(header.getName());
            if(ObjectUtils.isEmpty(clientIpFromHeader) && "unknown".equalsIgnoreCase(clientIpFromHeader)){
                String clientIp = clientIpFromHeader.split(",")[0];
                return clientIp;
            }
        }
        return request.getRemoteAddr();
    }
}
