package com.DSDAAA.service;

import com.DSDAAA.dto.system.LoginDto;
import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    Map<String, String> getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void save(SysUser sysUser);

    void update(SysUser sysUser);

    void removeById(Long id);
}
