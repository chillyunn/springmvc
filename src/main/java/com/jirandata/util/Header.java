package com.jirandata.util;

public enum Header {
    X_FORWARDED_FOR("X-Forwarded-For"),
    PROXY_CLIENT_IP("Proxy-Client-IP"),
    WL_PROXY_CLIENT_IP("WL-Proxy-Client-IP"),
    HTTP_X_FORWARDED_FOR("HTTP_X_FORWARDED_FOR"),
    HTTP_X_FORWARDED("HTTP_X_FORWARDED"),
    HTTP_X_CLUSTER_CLIENT_IP("HTTP_X_CLUSTER_CLIENT_IP"),
    HTTP_CLIENT_IP("HTTP_CLIENT_IP"),
    HTTP_FORWARDED_FOR("HTTP_FORWARDED_FOR"),
    HTTP_FORWARDED("HTTP_FORWARDED"),
    HTTP_VIA("HTTP_VIA"),
    REMOTE_ADDR("REMOTE_ADDR");


    private final String name;

    public String getName() {
        return name;
    }

    Header(String name){
        this.name = name;
    }
}
