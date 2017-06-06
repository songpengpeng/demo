package com.swallow.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.swallow.constant.MathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author 张峰 2017-05-05 21:37
 */
@Configuration
@PropertySource("classpath:/datasource.properties")
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        String[] activeProfiles = env.getActiveProfiles();
        String suffix = activeProfiles.length == 0 ? "dev" : activeProfiles[MathConstant.ZERO];
        String url = env.getProperty("jdbc.url." + suffix);
        String username = env.getProperty("jdbc.username." + suffix);
        String password = env.getProperty("jdbc.password." + suffix);
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setMaxActive(env.getProperty("maxActive", int.class));
        dataSource.setInitialSize(env.getProperty("initialSize", int.class));
        dataSource.setMaxWait(env.getProperty("maxWait", long.class));
        dataSource.setMinIdle(env.getProperty("minIdle", int.class));
        dataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("timeBetweenEvictionRunsMillis", long.class));
        dataSource.setMinEvictableIdleTimeMillis(env.getProperty("minEvictableIdleTimeMillis", long.class));
        dataSource.setValidationQuery(env.getProperty("validationQuery"));
        dataSource.setTestWhileIdle(env.getProperty("testWhileIdle", boolean.class));
        dataSource.setTestOnBorrow(env.getProperty("testOnBorrow", boolean.class));
        dataSource.setTestOnReturn(env.getProperty("testOnReturn", boolean.class));
        dataSource.setMaxOpenPreparedStatements(env.getProperty("maxOpenPreparedStatements", int.class));
        dataSource.setRemoveAbandoned(env.getProperty("removeAbandoned", boolean.class));
        dataSource.setRemoveAbandonedTimeout(env.getProperty("removeAbandonedTimeout", int.class));
        dataSource.setLogAbandoned(env.getProperty("logAbandoned", boolean.class));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
