package nimdanoob.knight.web;

import com.knight.common.redis.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    StringRedisTemplate stringRedisTemplate(){
        return new StringRedisTemplate(jedisConnectionFactory());
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}