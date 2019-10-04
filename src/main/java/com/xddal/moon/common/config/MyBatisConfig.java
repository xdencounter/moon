package com.xddal.moon.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created by xuedong on 2019/6/16.
 *
 * @author xuedong
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.xddal.moon.auto.dao", "com.xddal.moon.external"})
public class MyBatisConfig {
}
