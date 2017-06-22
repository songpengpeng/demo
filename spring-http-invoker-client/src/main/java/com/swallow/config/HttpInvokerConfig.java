package com.swallow.config;

import com.swallow.rpc.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * @author 张峰 2017-05-04 08:03
 */
@Configuration
public class HttpInvokerConfig {
    @Bean
    public HttpInvokerProxyFactoryBean userService() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/service/invoker.service");
        proxy.setServiceInterface(UserService.class);
        return proxy;
    }
}
