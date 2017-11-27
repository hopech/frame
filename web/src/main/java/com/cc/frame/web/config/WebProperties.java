package com.cc.frame.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CHENWEIJIA
 * @version V1.0, 2017-11-16
 * @see
 * @since V1.0
 */
@ConfigurationProperties(prefix = "xweb")
public class WebProperties {

    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
    	System.out.println("xweb默认的环境配置为：" + WebConfig.environment);
        this.environment = environment;
        WebConfig.environment = environment;
        System.out.println("xweb从外界注入的环境配置为：" + WebConfig.environment);
    }
}
