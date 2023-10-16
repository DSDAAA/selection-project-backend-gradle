package com.DSDAAA.controller;

import com.DSDAAA.dto.system.SysUserDto;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.service.SysUserService;
import com.DSDAAA.vo.common.Result;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户模块")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @Operation(summary = "分页查询")
    @PostMapping("/findPage/{pageNum}/{pageSize}")
    public Result findPage(@RequestBody SysUserDto sysUserDto,//@RequestBody 注解别导错包，否则数据获取不到。
                                              @PathVariable Integer pageNum,
                                              @PathVariable Integer pageSize){
        PageInfo<SysUser> pageInfo =  sysUserService.findPage(pageNum,pageSize,sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser){
        sysUserService.save(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result update(@RequestBody SysUser sysUser){
        sysUserService.update(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据id删除一条(逻辑删除)")
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id){
        sysUserService.removeById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
