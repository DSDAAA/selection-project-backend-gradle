package com.DSDAAA.service.impl;

import com.DSDAAA.dto.system.SysRoleDto;
import com.DSDAAA.entity.system.SysRole;
import com.DSDAAA.mapper.SysRoleMapper;
import com.DSDAAA.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findPage(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {
        //1.开启分页功能
        //底层，其实将分页信息封装Page对象，利用ThreadLocal绑定当前线程上。
        PageHelper.startPage(pageNum, pageSize);

        //2.分页查询
        Page<SysRole> page = sysRoleMapper.findPage(sysRoleDto); //返回是Page对象   Page extends ArrayList
        //return new PageInfo<>(page,5);
        return new PageInfo<>(page); //默认导航页 8
    }

    @Override
    public void save(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void removeById(Long id) {
        sysRoleMapper.removeById(id);
    }
}
