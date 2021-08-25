package com.jetbone.app.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Chris
 * @date 2021-08-23
 */
@Service
public class AsyncService {

    @Async
    public Future<String> sync1(String input) throws InterruptedException {
        System.out.println("当前线程：" + Thread.currentThread().getName() + " , input: " + input);
        Thread.sleep(3000);
        return new AsyncResult<>("sync1");
    }

    @Async
    public String sync2(String input) throws InterruptedException {
        System.out.println("当前线程：" + Thread.currentThread().getName() + " , input: " + input);
        Thread.sleep(3000);
        return "sync2";
    }

    @Async
    public String sync3(String input) throws InterruptedException {
        System.out.println("当前线程：" + Thread.currentThread().getName() + " , input: " + input);
        Thread.sleep(3000);
        return "sync3";
    }

    @Async
    public String sync4(String input) throws InterruptedException {
        System.out.println("当前线程：" + Thread.currentThread().getName() + " , input: " + input);
        Thread.sleep(3000);
        return "sync14";
    }
}
