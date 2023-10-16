package com.DSDAAA.service;

import com.DSDAAA.vo.system.ValidateCodeVo;

public interface ValidateCodeService {

    // 获取验证码图片
    ValidateCodeVo generateValidateCode();

}
