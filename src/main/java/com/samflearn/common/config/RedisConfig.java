package com.samflearn.common.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    private static final String REDISSON_HOST_PREFIX = "redis://";


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Lettuce라는 라이브러리를 활용해 Redis 연결을 관리하는 객체를 생성하고
        // Redis 서버에 대한 정보(host, port)를 설정한다.
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDISSON_HOST_PREFIX +host + ":" + port);
        config.useSingleServer().setDatabase(0);
        return Redisson.create(config);
    }

}
