package com.jetbone.others.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

/**
 * @author Chris
 * @date 2021-10-20
 */
@Slf4j
public class TestEndProcessor implements Flow.Subscriber<String> {
    
    private Flow.Subscription subscription;
    
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestEndProcessor.onSubscribe");
        this.subscription = subscription;
        subscription.request(1);

    }

    @Override
    public void onNext(String item) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestEndProcessor.onNext, 最终消费：" + item);

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestEndProcessor.onError");
    }

    @Override
    public void onComplete() {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestEndProcessor.onComplete");
    }
}
