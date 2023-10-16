package com.DSDAAA.service.impl;

import cn.hutool.core.util.StrUtil;
import com.DSDAAA.AuthContextUtil;
import com.DSDAAA.constant.CacheConstant;
import com.DSDAAA.dto.system.LoginDto;
import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.exception.GuiguException;
import com.DSDAAA.mapper.SysUserMapper;
import com.DSDAAA.service.SysUserService;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.DSDAAA.vo.system.LoginVo;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public void logout(String token) {
        redisTemplate.delete(CacheConstant.USER_LOGIN_PREFIX+token);
    }

/*    @Override
    public Map<String, String> getUserInfo(String token) {
        String sysUserJsonString = redisTemplate.opsForValue().get(CacheConstant.USER_LOGIN_PREFIX +token);
        SysUser sysUser = JSON.parseObject(sysUserJsonString, SysUser.class);
        Map<String, String> map = new HashMap<>();
        map.put("avatar",sysUser.getAvatar());
        map.put("name",sysUser.getName());
        return map;
    }*/

    @Override
    public Map<String, String> getUserInfo(String token) {
        SysUser sysUser = AuthContextUtil.get();
        Map<String, String> map = new HashMap<>();
        map.put("avatar",sysUser.getAvatar());
        map.put("name",sysUser.getName());
        return map;
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        log.debug("开始登录了...");
        //======增加验证码校验规则===============================================
        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key

        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX + codeKey); //  redis中验证码5分钟有效
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }

        //=====================================================================

        // 验证通过删除redis中的验证码
        //redisTemplate.delete(CacheConstant.USER_LOGIN_VALIDATECODE_PREFIX + codeKey) ;

        String userName = loginDto.getUserName();
        String passwordInput = loginDto.getPassword(); //登录表单 明文密码
        //用户名和密码是否为空
        if(StrUtil.isEmpty(userName) || StrUtil.isEmpty(passwordInput)){
            //throw new RuntimeException("参数不能为空");
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //根据用户名称查询用户
        SysUser sysUser =  sysUserMapper.getSysUserByUserName(userName);
        if(sysUser == null){
            //throw new RuntimeException("用户名不正确");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        String passworddbMd5 = sysUser.getPassword(); // 数据库存储MD5加密密文

        //密码是否正确
        String passwordInputMd5 = DigestUtils.md5DigestAsHex(passwordInput.getBytes());
        /**
         * MD5: 特点
         *  1.不可逆的加密算法：
         *  2.密文固定长度   16个字节       1个字节8位，拆分  高四位  低四位    0000 0000      1111 1111
         *  3.原文不变，密码不变
         *
         *  96e79218965eb72c92a549dd5a330112    32个字符长的密文  每一个字符是16进制数   0 ~9 a~ f
         *
         *  作用：用来验证文件、资源是否被篡改。
         */
        if(!passworddbMd5.equals(passwordInputMd5)){
            //throw new RuntimeException("密码错误");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //账号是否被禁用
        Integer status = sysUser.getStatus();
        if(status == 0){
            //throw new RuntimeException("用户账号已禁用");
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //给用户生成token令牌,保存到redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        //redisTemplate.opsForValue().set("user:login:" +token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);
        //redisTemplate.opsForValue().set(CacheConstant.USER_LOGIN_PREFIX +token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);

        // TODO  临时代码，上线需要改回来
        redisTemplate.opsForValue().set(CacheConstant.USER_LOGIN_PREFIX +token, JSON.toJSONString(sysUser));

        log.debug("登录成功...");

        //封装LoginVo返回结果
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token(token);
        //返回数据
        return loginVo;
    }




    @Override
    public PageInfo<SysUser> findPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        //1.开启分页功能
        //底层，其实将分页信息封装Page对象，利用ThreadLocal绑定当前线程上。
        PageHelper.startPage(pageNum,pageSize);

        //2.分页查询
        Page<SysUser> page = sysUserMapper.findPage(sysUserDto); //返回是Page对象   Page extends ArrayList
        //return new PageInfo<>(page,5);
        return new PageInfo<>(page); //默认导航页 8
    }

    @Override
    public void save(SysUser sysUser) {
        SysUser sysUserdb =  sysUserMapper.getSysUserByUserName(sysUser.getUserName());
        if(sysUserdb != null){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        sysUser.setStatus(1);
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void removeById(Long id) {
        sysUserMapper.removeById(id);
    }
}
