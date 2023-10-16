package com.DSDAAA.controller;

import com.DSDAAA.dto.system.LoginDto;
import com.DSDAAA.service.SysUserService;
import com.DSDAAA.service.ValidateCodeService;
import com.DSDAAA.vo.common.Result;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.DSDAAA.vo.system.LoginVo;
import com.DSDAAA.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/index")
@Tag(name = "登录")
//allowCredentials = "true" 跨域请求默认不允许携带cookie,true允许
//originPatterns = "*"  星表示任何地址都可以跨域访问服务器。  originPatterns = "http://localhost:3001/" 允许特定地址跨域。
//allowedHeaders = "*"  星表示跨域时可以携带任何请求头信息。
//@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*") // maxAge默认值是30min
public class IndexController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    ValidateCodeService validateCodeService;


    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户信息")
    public Result getUserInfo(@RequestHeader(value = "token",required = true) String token){
        //课件返回SysUser实体对象，但是，这个对象含有密码，以及其他属性值；密码返回前端不安全，其他属性值，前端也使用不到，无需返回。
        //所以，只返回前端必须两个值：头像、用户姓名  用于欢迎信息。
        Map<String,String> map = sysUserService.getUserInfo(token);
        return Result.build(map,ResultCodeEnum.SUCCESS);
    }


    @GetMapping("/generateValidateCode")
    @Operation(summary = "获取验证码")
    public Result generateValidateCode(){
        ValidateCodeVo validateCodeVo =  validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }



    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){ //@RequestBody千万别导错包，否则，获取不到数据

        //int i = 1/0;
        LoginVo loginVo =  sysUserService.login(loginDto);

        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    /*@Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){ //@RequestBody千万别导错包，否则，获取不到数据
        try {
            LoginVo loginVo =  sysUserService.login(loginDto);
            return Result.build(loginVo, ResultCodeEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(null, 201,e.getMessage());
        }
    }*/
}
