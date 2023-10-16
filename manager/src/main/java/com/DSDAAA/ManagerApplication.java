package com.DSDAAA;

import com.DSDAAA.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@MapperScan(basePackages = "com.DSDAAAmanager.mapper")
//@ComponentScan(basePackages = "com.atguigu.spzx") 默认扫描主程序所在的包以及子包
@EnableConfigurationProperties(value = {UserAuthProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
}
