package com.knight.ucenter;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.knight",},
        exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
@PropertySource({"classpath:dubbo.properties","classpath:jdbc.properties"})
@ImportResource({"classpath:spring/*.xml"})
public class KnightUserServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(KnightUserServiceApplication.class, args);
    }

}
