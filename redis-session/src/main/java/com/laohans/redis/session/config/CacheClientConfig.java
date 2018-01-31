package com.laohans.redis.session.config;

import com.swallow.cache.service.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * @author laohans xxx
 */
@Configuration
public class CacheClientConfig {
    @Bean
    public HttpInvokerProxyFactoryBean redisService() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://192.168.1.82:8082/cache/redisService");
        proxy.setServiceInterface(RedisService.class);
        return proxy;
    }
}
