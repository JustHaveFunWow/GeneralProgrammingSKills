package com.knight.upms.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.knight",},exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.knight.*.dao.mapper")
public class KnightUpmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnightUpmsServiceApplication.class,args);
    }
}