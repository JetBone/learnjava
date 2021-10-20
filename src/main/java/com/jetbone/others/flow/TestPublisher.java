package com.jetbone.others.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Chris
 * @date 2021-10-20
 */
@Slf4j
public class TestPublisher extends SubmissionPublisher<String> {

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestPublisher.subscribe");
        super.subscribe(subscriber);
    }
}
