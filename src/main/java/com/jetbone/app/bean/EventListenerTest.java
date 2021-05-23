package com.jetbone.app.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

/**
 * @author Chris
 * @date 2021-05-23
 */
@Slf4j
@Configuration
public class EventListenerTest {

    @EventListener(ApplicationStartedEvent.class)
    @Order(10)
    public void eventTest1() {
        log.info("EventListenerTest eventTest1 order 10");
    }

    @EventListener(ApplicationStartedEvent.class)
    @Order(20)
    public void eventTest2() {
        log.info("EventListenerTest eventTest2 order 20");
    }

    @EventListener(ApplicationStartedEvent.class)
    @Order(30)
    public void eventTest3() {
        log.info("EventListenerTest eventTest3 order 30");
    }

    @EventListener(ApplicationStartedEvent.class)
    @Order
    public void eventTest4() {
        log.info("EventListenerTest eventTest4 order default");
    }

    @Bean
    public ApplicationListener<ApplicationStartedEvent> listener() {
        return new ApplicationListener<ApplicationStartedEvent>() {
            @Override
//            @Order(17)
            public void onApplicationEvent(ApplicationStartedEvent event) {
                log.info("inner eventTest order 17");
            }
        };
    }
}
