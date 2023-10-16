package com.DSDAAA.exception;


import com.DSDAAA.vo.common.ResultCodeEnum;

public class LoginException extends RuntimeException {

    private Integer code ;          // 错误状态码
    private String message ;        // 错误消息

    private ResultCodeEnum resultCodeEnum ;     // 封装错误状态码和错误消息

    public LoginException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public LoginException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
