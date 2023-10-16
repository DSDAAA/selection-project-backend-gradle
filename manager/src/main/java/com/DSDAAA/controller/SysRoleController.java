package com.DSDAAA.controller;

import com.DSDAAA.dto.system.SysRoleDto;
import com.DSDAAA.entity.system.SysRole;
import com.DSDAAA.service.SysRoleService;
import com.DSDAAA.vo.common.Result;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色模块")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @Operation(summary = "分页查询")
    @PostMapping("/findPage/{pageNum}/{pageSize}")
    public Result findPage(@RequestBody SysRoleDto sysRoleDto,//@RequestBody 注解别导错包，否则数据获取不到。
                                              @PathVariable Integer pageNum,
                                              @PathVariable Integer pageSize){
        PageInfo<SysRole> pageInfo =  sysRoleService.findPage(pageNum,pageSize,sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole){
        sysRoleService.save(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result update(@RequestBody SysRole sysRole){
        sysRoleService.update(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据id删除一条(逻辑删除)")
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id){
        sysRoleService.removeById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
