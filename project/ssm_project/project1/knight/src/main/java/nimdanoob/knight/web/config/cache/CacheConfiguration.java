package nimdanoob.knight.web.config.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * @author ZSX
 */

@Configuration
@EnableCaching // 标注启动缓存
public class CacheConfiguration {


    /**
     * ehcache 主要的管理器
     *
     * @param
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }


}
