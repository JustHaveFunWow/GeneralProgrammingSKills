package nimdanoob.knight.web;

import com.knight.common.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

// 没有配置数据库 暂时，exclude autoconfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("nimdanoob.knight.web.domain.mapper")
@EnableCaching
@ImportResource({"classpath*:applicationContext.xml"})
public class KnightApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KnightApplication.class, args);
	}
}
