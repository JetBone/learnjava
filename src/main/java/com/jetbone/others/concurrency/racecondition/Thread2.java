package com.jetbone.others.concurrency.racecondition;

/**
 * Created by Chris on 2020/4/27
 */
public class Thread2 implements Runnable {
    private Counter counter;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter.count(2);
    }

    public Thread2(Counter counter) {
        this.counter = counter;
    }
}
