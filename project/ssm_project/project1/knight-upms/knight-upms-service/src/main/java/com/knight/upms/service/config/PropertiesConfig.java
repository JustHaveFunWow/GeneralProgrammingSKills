package com.knight.upms.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:dubbo.properties","classpath:jdbc.properties"})
@ImportResource({"classpath:spring/*.xml"})
public class PropertiesConfig {
}
