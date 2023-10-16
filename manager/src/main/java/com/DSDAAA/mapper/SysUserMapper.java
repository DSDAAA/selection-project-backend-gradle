package com.DSDAAA.mapper;

import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface SysUserMapper {
    SysUser getSysUserByUserName(String userName);



    Page<SysUser> findPage(SysUserDto sysUserDto);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void removeById(Long id);
}
