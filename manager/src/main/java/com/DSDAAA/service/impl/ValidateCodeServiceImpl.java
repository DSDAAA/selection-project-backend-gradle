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
    RedisTemplate<String,String> redisTemplate; //SpringBoot自动化配置

    @Override
    public ValidateCodeVo generateValidateCode() {

        //1.通过CaptchaUtil工具类生成验证码
        //创建圆圈干扰的验证码 width – 图片宽 height – 图片高 codeCount – 字符个数 circleCount – 干扰圆圈条数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = captcha.getCode();

        //2.生成redis中存储验证码的codeKey
        String codeKey =  UUID.randomUUID().toString().replace("-", "");
        String codeImageBase64 = CacheConstant.USER_LOGIN_VALIDATECODE_IMAGE_PREFIX+captcha.getImageBase64();

        //3.将验证码保存redis中，便于登录请求，对验证码校验
        //redisTemplate.opsForValue().set("user:login:validatecode:"+codeKey,codeValue,5, TimeUnit.MINUTES); //验证码5分钟有效
        redisTemplate.opsForValue().set(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX +codeKey,codeValue,5, TimeUnit.MINUTES); //验证码5分钟有效

        //封装后端生成验证码信息
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey); //redis存在验证码key
        validateCodeVo.setCodeValue(codeImageBase64); //base64编码字符串，带特殊前缀：前端<img src="">通过img标签回显图片

        return validateCodeVo;
    }
}
