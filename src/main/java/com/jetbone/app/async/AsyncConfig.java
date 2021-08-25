package com.jetbone.app.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Chris
 * @date 2021-08-23
 */
@Configuration
public class AsyncConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        var threadPool = new ThreadPoolTaskExecutor();
        threadPool.setThreadNamePrefix("Custom-Pool-");
        threadPool.setMaxPoolSize(10);
        threadPool.setCorePoolSize(10);
        return threadPool;
    }
}
