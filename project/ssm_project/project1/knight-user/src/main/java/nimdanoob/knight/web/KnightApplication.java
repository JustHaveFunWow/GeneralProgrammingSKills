package nimdanoob.knight.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

// 没有配置数据库 暂时，exclude autoconfig
@SpringBootApplication()
@MapperScan("nimdanoob.knight.web.dao.mapper")
@EnableCaching
@ImportResource({"classpath*:applicationContext.xml"})
public class KnightApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KnightApplication.class, args);

	}
}
