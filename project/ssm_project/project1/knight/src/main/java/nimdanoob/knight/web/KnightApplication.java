package nimdanoob.knight.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

// 没有配置数据库 暂时，exclude autoconfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("nimdanoob.knight.web.domain.mapper")
@EnableCaching
public class KnightApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnightApplication.class, args);
	}
}
