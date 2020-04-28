package com.jetbone.others.concurrency.fairlock;

/**
 * Created by Chris on 2020/4/28
 *
 * 这里的实现方式只是自定了锁，实现了 synchronized 功能，并将 wait 和 notify 方法包装一层变的可控
 * 虽然没有实现 fairness，但是为之后的 fairness 实现打下了基石
 */
public class Lock {

    // 用来保存是否有线程正在占用锁
    private boolean isLocked = false;
    // 用来保存当前获取锁的线程
    private Thread lockingThread = null;

    // 外部的 synchronized 去掉，改为加到这里
    public synchronized void lock() throws InterruptedException {
        // 先判断资源是否已经被加锁，如果已经被加锁，则进入 waiting 状态
        while (isLocked) {
            this.wait();
        }
        // 设置加锁状态
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        // 在进行 unlock 操作的时候，首先要判断执行的 线程是否是之前加锁的线程
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        // 在唤醒其它线程前，先设置isLocked 为 false，并清空当前正在执行的线程信息
        isLocked = false;
        lockingThread = null;
        // 唤醒其它线程
        this.notify();
    }
}
