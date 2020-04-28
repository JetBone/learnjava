package com.jetbone.others.concurrency.racecondition;

/**
 * Created by Chris on 2020/4/27
 */
public class Thread1 implements Runnable {

    private Counter counter;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter.count(1);
    }

    public Thread1(Counter counter) {
        this.counter = counter;
    }
}
