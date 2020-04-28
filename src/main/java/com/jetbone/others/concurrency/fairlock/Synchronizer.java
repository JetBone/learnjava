package com.jetbone.others.concurrency.fairlock;

/**
 * Created by Chris on 2020/4/28
 */
public class Synchronizer {

    Lock lock = new Lock();

    // 去掉 synchronized 修饰
    public void doSynchronized() throws InterruptedException{
        // 这里使用 lock() 方法代替 synchronized 方法，所以 Lock 类里必须实现 synchronized 功能
        this.lock.lock();
        // to something
        this.lock.unlock();
    }
}
