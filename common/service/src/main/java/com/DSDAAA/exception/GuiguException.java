package com.DSDAAA.exception;

import com.DSDAAA.vo.common.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类:可以根据不同的业务，可以定义不同类型的异常类。
 * 推荐继承：RuntimeException
 *      为啥：业务层Spring 声明式事务  回滚策略  ： 默认 遇到RuntimeException异常自动回滚。
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GuiguException extends RuntimeException{

    private Integer code; //只封装 状态码
    private String message; // 只封装 消息

    ResultCodeEnum resultCodeEnum;  //封装错误状态码和错误消息


    public GuiguException(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    public GuiguException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

}
