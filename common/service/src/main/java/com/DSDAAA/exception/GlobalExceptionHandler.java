package com.DSDAAA.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.DSDAAA.vo.common.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null , 201,"出现了异常") ;
    }
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result error(LoginException e){
        e.printStackTrace();
        return Result.build(null , 201,"出现特定异常") ;
    }
}
