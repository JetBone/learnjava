package com.jetbone.app.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author Chris
 * @date 2021-05-23
 */
@Slf4j
@Component
public class EventTest3 implements ApplicationListener<ApplicationStartedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("This is EventTest3 order 25");
    }

    @Override
    public int getOrder() {
        return 25;
    }
}
