package com.DSDAAA.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "验证码响应结果实体类")
public class ValidateCodeVo {

    @Schema(description = "验证码key")
    private String codeKey ;        // 验证码的key

    @Schema(description = "验证码value")
    private String codeValue ;      // 图片验证码对应的字符串数据

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public String getCodeValue() {
        return codeValue;
    }
}
