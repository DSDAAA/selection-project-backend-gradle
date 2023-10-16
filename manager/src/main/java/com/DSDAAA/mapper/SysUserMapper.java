package com.DSDAAA.mapper;

import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户数据
     *
     * @param userName
     * @return
     */
    SysUser selectByUsername(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    SysUser findByUserName(String name);

    void saveSysUser(SysUser sysUser);

    // com.atguigu.spzx.manager.mapper.SysUserMapper
    void updateSysUser(SysUser sysUser);

    // com.atguigu.spzx.manager.mapper.SysUserMapper
    void deleteById(Long userId);
}
