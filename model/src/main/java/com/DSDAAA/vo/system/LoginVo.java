package com.DSDAAA.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "登录成功响应结果实体类")
public class LoginVo {

    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refresh_token ;

    public LoginVo() {
    }

    public LoginVo(String token, String refresh_token) {
        this.token = token;
        this.refresh_token = refresh_token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken() {
        return token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}
