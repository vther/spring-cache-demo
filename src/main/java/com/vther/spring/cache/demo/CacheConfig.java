package com.vther.spring.cache.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    @Primary
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("books", "books2");
    }

    @Bean
    public CacheManager cacheManager2() {
        return new ConcurrentMapCacheManager("books3");
    }
}
