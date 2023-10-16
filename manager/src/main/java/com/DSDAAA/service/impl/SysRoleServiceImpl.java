package com.DSDAAA.service.impl;

import com.DSDAAA.dto.system.SysRoleDto;
import com.DSDAAA.entity.system.SysRole;
import com.DSDAAA.mapper.SysRoleMapper;
import com.DSDAAA.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList);
        return pageInfo;
    }

    // com.atguigu.spzx.manager.service.impl.SysRoleServiceImpl
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    // com.atguigu.spzx.manager.service.impl.SysRoleServiceImpl
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    // com.atguigu.spzx.manager.service.impl.SysRoleServiceImpl
    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }
}
