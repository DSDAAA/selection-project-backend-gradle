package com.DSDAAA.config;

import com.DSDAAA.interceptor.LoginAuthInterceptor;
import com.DSDAAA.properties.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 统一跨域设置
 * 登录验证拦截器配置
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    UserAuthProperties authProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(authProperties.getNoAuthUrls())
                .addPathPatterns("/**"); // Ant路径
    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns("/admin/system/index/login" ,
                        "/admin/system/index/generateValidateCode")
                .addPathPatterns("/**"); // Ant路径
    }*/

    /**
     * //allowCredentials = "true" 跨域请求默认不允许携带cookie,true允许
     * //originPatterns = "*"  星表示任何地址都可以跨域访问服务器。  originPatterns = "http://localhost:3001/" 允许特定地址跨域。
     * //allowedHeaders = "*"  星表示跨域时可以携带任何请求头信息。
     * //@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*") // maxAge默认值是30min
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")                  // post get put delete ...
                .allowedHeaders("*") ;                // 允许所有的请求头
    }
}
