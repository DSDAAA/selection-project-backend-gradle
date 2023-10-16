package com.DSDAAA.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO 数据传输对象： 前端分离开发，前端向后端提交请求参数数据（例如：条件查询）,用DTO对象接收。
 */
@Data
@Schema(description = "请求参数实体类")
public class SysRoleDto {

    @Schema(description = "角色名称")
    private String roleName ;

}
