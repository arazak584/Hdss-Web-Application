package org.arn.hdsscapture;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


@Configuration
public class CacheConfig {

    @Bean
    Cache<String, ByteArrayResource> zipFileCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(10) // Adjust the maximum size based on your requirements
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

}
