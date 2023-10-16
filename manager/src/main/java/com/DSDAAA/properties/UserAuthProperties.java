package com.DSDAAA.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spzx.auth")      // 前缀不能使用驼峰命名
public class UserAuthProperties {
    private List<String> noAuthUrls ;

    public UserAuthProperties() {
    }

    public UserAuthProperties(List<String> noAuthUrls) {
        this.noAuthUrls = noAuthUrls;
    }

    public List<String> getNoAuthUrls() {
        return noAuthUrls;
    }

    public void setNoAuthUrls(List<String> noAuthUrls) {
        this.noAuthUrls = noAuthUrls;
    }
}
