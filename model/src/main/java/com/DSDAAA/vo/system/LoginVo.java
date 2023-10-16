package com.DSDAAA.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * VO :  视图对象，前后端分离开发，后端返回数据给前端，数据可以封装成VO对象，通过result进行统一结果返回。
 */
@Data
@Schema(description = "登录成功响应结果实体类")
public class LoginVo {

    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refresh_token ;

}
