package com.DSDAAA.service;


import com.DSDAAA.dto.system.SysRoleDto;
import com.DSDAAA.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {
    PageInfo<SysRole> findPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto);

    void save(SysRole sysRole);

    void update(SysRole sysRole);

    void removeById(Long id);
}
