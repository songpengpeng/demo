package com.swallow.demo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 老汉憨憨 2017-06-05 23:48
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class DataSourceConfigTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() {
        jdbcTemplate.query("select * from t_user WHERE user_id = ? ", new Object[]{"admin"}, (resultSet, i) -> {
            System.out.println(resultSet.getString("user_id"));
            return null;
        });
    }

}
