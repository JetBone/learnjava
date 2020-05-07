package com.jetbone.others.concurrency.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2020/5/6
 *
 * 针对 write reentrance 进行优化后的类
 * 写再获取写
 */
public class WriteReentranceLock {

    // 用来保存读锁实例以及 reentrance 计数
    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    // 写锁获取的次数
    private int writeAccesses    = 0;
    // 写锁请求数量
    private int writeRequests    = 0;
    // 当前正在写的线程
    private Thread writingThread = null;

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        // 将之前新增的写请求减去
        writeRequests--;
        // 将获得写的次数+1
        writeAccesses++;
        // 写线程等于当前线程
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writeAccesses--;
        // 判断是否已经将全部的 reentrance 锁释放
        if (writeAccesses == 0) {
            writingThread = null;
            notifyAll();
        }
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        // 有线程正在读，则不能获取锁
        if (hasReader()) return false;
        // 在上述条件不成立后，如果当前写线程为空，则可以获取锁
        if (writingThread == null) return true;
        // 在上述条件均不成立后，如果当前写的线程正好也是自己，则可以获取锁
        if (isWriter(callingThread)) return true;
        // 在上述条件均不成立后，不能获取锁
        return false;
    }

    // 判断是否有读线程
    private boolean hasReader() {
        return readingThreads.size() > 0;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }
}
