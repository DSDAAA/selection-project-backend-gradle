package com.DSDAAA.exception;


import com.DSDAAA.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理类：
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e 只要是Exception类型异常，都进行处理
     * @return 状态码和异常消息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody //将result对象转换为json返回。否则，会进行视图解析，找页面：报404错误。
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null, 201,"系统异常");
    }

    /**
     * 特定异常类型处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.build(null , 20007,"出现特定异常，算术异常") ;
    }

    /**
     * 处理自定义异常
     * @param e GuiguException
     * @return
     */
    @ExceptionHandler(value = GuiguException.class)
    @ResponseBody
    public Result error(GuiguException e){
        e.printStackTrace();
        return Result.build(null, e.getCode(),e.getMessage());
    }
}
