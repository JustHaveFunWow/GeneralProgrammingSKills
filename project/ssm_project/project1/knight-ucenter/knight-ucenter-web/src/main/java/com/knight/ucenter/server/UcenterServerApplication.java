package com.knight.ucenter.server;

import com.knight.upms.api.UpmsApiService;
import com.knight.upms.api.UpmsLogService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication(scanBasePackages = {"com.knight",})
@MapperScan("com.knight.ucenter.dao.mapper")
public class UcenterServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UcenterServerApplication.class, args);

    }
}
