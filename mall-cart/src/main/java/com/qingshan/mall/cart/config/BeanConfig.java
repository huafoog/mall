package com.qingshan.mall.cart.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
@Slf4j
public class BeanConfig {
//    @Value("${async.executor.thread.core_pool_size}")
    private Integer corePoolSize;
//    @Value("${async.executor.thread.max_pool_size}")
    private Integer maxPoolSize;
//    @Value("${async.executor.thread.queue_capacity}")
    private Integer queueCapacity;
//    @Value("${async.executor.thread.name.prefix}")
    private String namePrefix;
//    @Value("${async.executor.thread.keep_alive_seconds}")
    private Integer keepAliveSeconds;
    @Bean
    public ThreadPoolExecutor taskExecutor() {

        if (corePoolSize== null){
            corePoolSize = 10;
        }
        if (maxPoolSize== null){
            maxPoolSize = 10;
        }
        if (keepAliveSeconds== null){
            keepAliveSeconds = 30000;
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
