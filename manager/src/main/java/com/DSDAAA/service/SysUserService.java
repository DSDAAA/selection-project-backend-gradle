package com.DSDAAA.service;

import com.DSDAAA.dto.system.LoginDto;
import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {

    /**
     * 根据用户名查询用户数据
     *
     * @return
     */
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
