package com.knight.upms.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.knight.common.util.SpringContextUtil;
import com.knight.upms.api.UpmsSystemService;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.knight",},
        exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
//@ComponentScan("com.knight.*")
public class KnightUpmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnightUpmsServiceApplication.class,args);
        SpringContextUtil.getBean(UpmsSystemService.class).selectUpmsSystemByName("test");
    }
}
