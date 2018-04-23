package com.knight.ucenter.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource({"classpath:spring/*.xml"})
public class PropertiesConfig {
}
