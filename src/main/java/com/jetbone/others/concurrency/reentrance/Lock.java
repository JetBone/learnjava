package com.jetbone.others.concurrency.reentrance;

/**
 * Created by Chris on 2020/4/30
 */
public class Lock{

    boolean isLocked = false;
    Thread  lockedBy = null;
    int     lockedCount = 0;

    // 该方法没有考虑到 lock reentrance 现象，如果同一个线程连续两次进入这个方法，那么第二次将会走到 wait() 方法，造成死锁
    public synchronized void lock0() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    // 该方法会先判断自己是否已经获得锁，防止出现 reentrance 后的死锁问题
    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }


    public synchronized void unlock() {
        if(Thread.currentThread() == this.lockedBy) {
            lockedCount--;

            if(lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
