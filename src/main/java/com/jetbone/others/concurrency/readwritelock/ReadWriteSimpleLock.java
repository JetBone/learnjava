package com.jetbone.others.concurrency.readwritelock;

/**
 * Created by Chris on 2020/4/30
 *
 * 没有处理 reentrance 问题
 */
public class ReadWriteSimpleLock {

    // 记录有多少个获取到读锁的数量
    private int readers = 0;
    // 记录有多少个写，指已经获取到写锁的数量
    private int writers = 0;
    // 记录有多少个写请求，指没有获取到写锁，等待获取写锁的请求数量
    private int writeRequest = 0;

    public synchronized void lockRead() throws InterruptedException {
        // 如果有线程在写，或者有线程请求写，则 block
        while (writers > 0 || writeRequest > 0) {
            wait();
        }
        // 如果没有线程在写，则将 readers + 1
        readers++;
    }

    public synchronized void unlockRead() throws InterruptedException {
        // 现将 readers - 1，然后唤醒其它所有线程
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        // 写请求 + 1，如果获取到了写锁则再减掉
        writeRequest++;
        // 如果有线程获得了读锁或者写锁，则block
        while (readers > 0 || writers > 0) {
            wait();
        }
        // 如果没有线程获得读或写锁，则自己可以获得写锁
        writers++;
        writeRequest--;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writers--;
        notifyAll();
    }
}
