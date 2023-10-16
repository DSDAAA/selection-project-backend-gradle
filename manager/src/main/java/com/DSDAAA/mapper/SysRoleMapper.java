package com.DSDAAA.mapper;


import com.DSDAAA.dto.system.SysRoleDto;
import com.DSDAAA.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;

@Mapper
public interface SysRoleMapper {

    Page<SysRole> findPage(@Param("dto") SysRoleDto sysRoleDto);

    void insert(SysRole sysRole);

    void update(SysRole sysRole);

    void removeById(Long id);
}
