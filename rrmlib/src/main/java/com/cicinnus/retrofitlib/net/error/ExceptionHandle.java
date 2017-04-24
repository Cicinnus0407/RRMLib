package com.cicinnus.retrofitlib.net.error;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 错误处理提示类
 */

public abstract class ExceptionHandle {
    private static final String SOCKET_TIMEOUT_EXCEPTION = "服务器响应超时，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，稍后重试";
    private static final String UNKNOWN_HOST_EXCEPTION = "服务器地址错误，稍后重试";
    private static final String JSON_PARSE_EXCEPTION = "Json解析出错";
    private static final String UNKNOWN_EXCEPTION = "未知异常";


    public static String handleException(Throwable t){
        String ERROR_MSG;
        if (t instanceof SocketTimeoutException) {
            ERROR_MSG = SOCKET_TIMEOUT_EXCEPTION;
        } else if (t instanceof ConnectException) {
            ERROR_MSG = CONNECT_EXCEPTION;
        } else if (t instanceof UnknownHostException) {
            ERROR_MSG = UNKNOWN_HOST_EXCEPTION;
        } else if(t instanceof JsonParseException){
            ERROR_MSG = JSON_PARSE_EXCEPTION;
        }else {
            ERROR_MSG = UNKNOWN_EXCEPTION;
        }
        return ERROR_MSG;
    }


}
