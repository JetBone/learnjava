package com.jetbone.others.concurrency.reentrance;

/**
 * Created by Chris on 2020/4/30
 */
public class Reentrant {
    Lock lock = new Lock();

    // reentrance
    public void outer() throws InterruptedException {
        lock.lock();
        inner();
        lock.unlock();
    }

    public synchronized void inner() throws InterruptedException {
        lock.lock();
        //do something
        lock.unlock();
    }
}
