package com.swallow.config;

import com.swallow.rpc.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author 张峰 2017-05-04 07:40
 */
@Configuration
public class HttpInvokerConfig {
    @Bean(name = "/invoker.service")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(UserService userService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

}
