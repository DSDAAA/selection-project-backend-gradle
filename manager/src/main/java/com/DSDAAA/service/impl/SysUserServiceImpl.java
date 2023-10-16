package com.DSDAAA.service.impl;

import cn.hutool.core.util.StrUtil;
import com.DSDAAA.constant.CacheConstant;
import com.DSDAAA.dto.system.LoginDto;
import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.exception.LoginException;
import com.DSDAAA.mapper.SysUserMapper;
import com.DSDAAA.service.SysUserService;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.DSDAAA.vo.system.LoginVo;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {

        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key
        String redisCode = redisTemplate.opsForValue().get(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX + codeKey);
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
            throw new LoginException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 验证通过删除redis中的验证码
        redisTemplate.delete(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX + codeKey);
        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUsername(loginDto.getUserName());
        if (sysUser == null) {
            throw new LoginException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!md5InputPassword.equals(sysUser.getPassword())) {
            throw new LoginException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(CacheConstant.USER_LOGIN_PREFIX + token, JSON.toJSONString(sysUser), 30, TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }

    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(CacheConstant.USER_LOGIN_PREFIX + token);
    }

    // com.atguigu.spzx.manager.service.impl
    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo(sysUserList);
        return pageInfo;
    }

    // com.atguigu.spzx.manager.service.impl.SysUserServiceImpl
    @Override
    public void saveSysUser(SysUser sysUser) {

        // 根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName());
        if (dbSysUser != null) {
            throw new LoginException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser);
    }

    // com.atguigu.spzx.manager.service.impl.SysUserServiceImpl
    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    // com.atguigu.spzx.manager.service.impl.SysUserServiceImpl
    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }
}
