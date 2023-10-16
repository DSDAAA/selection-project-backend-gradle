package com.DSDAAA.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.DSDAAA.constant.CacheConstant;
import com.DSDAAA.service.ValidateCodeService;
import com.DSDAAA.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {

        // 使用hutool工具包中的工具类生成图片验证码
        //参数：宽  高  验证码位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = circleCaptcha.getCode();

        // 生成uuid作为图片验证码的key
        String codeKey = UUID.randomUUID().toString().replace("-", "");
        String codeImageBase64 = circleCaptcha.getImageBase64();
        // 将验证码存储到Redis中
        redisTemplate.opsForValue().set(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX + codeKey, codeValue, 5, TimeUnit.MINUTES);

        // 构建响应结果数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue(CacheConstant.USER_LOGIN_VALIDTECODE_IMAGE_PREFIX + codeImageBase64);

        // 返回数据
        return validateCodeVo;
    }
}
