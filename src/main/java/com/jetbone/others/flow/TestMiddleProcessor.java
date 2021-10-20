package com.jetbone.others.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Chris
 * @date 2021-10-20
 */
@Slf4j
public class TestMiddleProcessor extends SubmissionPublisher<String> implements Flow.Subscriber<String> {

    private int count = 1;
    private Flow.Subscription subscription;

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestMiddleProcessor.subscribe");
        super.subscribe(subscriber);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestMiddleProcessor.onSubscribe");
        this.subscription = subscription;
        subscription.request(3);
    }

    @Override
    public void onNext(String item) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestMiddleProcessor.onNext");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        submit(item + " middle count: " + count);
        count++;

        subscription.request(3);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestMiddleProcessor.onError");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        log.info("线程：" + Thread.currentThread().getName() + " - 执行 TestMiddleProcessor.onComplete");
    }
}
