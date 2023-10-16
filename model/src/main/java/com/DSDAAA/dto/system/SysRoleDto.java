package com.DSDAAA.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "请求参数实体类")
public class SysRoleDto {

    @Schema(description = "角色名称")
    private String roleName ;

    public SysRoleDto(String roleName) {
        this.roleName = roleName;
    }

    public SysRoleDto() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
