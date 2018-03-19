package nimdanoob.knight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 没有配置数据库 暂时，exclude autoconfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KnightApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnightApplication.class, args);
	}
}
