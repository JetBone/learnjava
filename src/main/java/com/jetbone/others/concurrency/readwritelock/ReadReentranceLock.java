package com.jetbone.others.concurrency.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2020/4/30
 *
 * 针对 read reentrance 进行优化后的类
 * 读再获取读
 */
public class ReadReentranceLock {
    // 用于保存获得读锁的线程，替代原来的 readers，key 是当前线程实例，value 是重复获取写锁的次数，用来做 reentrance
    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    // 获得写锁的线程数量
    private int writers = 0;
    // 等待获取写锁的线程数量，及请求写锁的数量
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        // 将当前读锁线程存入 map 当中
        readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);

    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        int accessCount = getReadAccessCount(callingThread);
        // 判断，如果 count 为 1，则不需要 -1，直接删除即可，因为 count 是从 1 开始保存的，所以不存在 0
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, accessCount - 1);
        }
        notifyAll();
    }

    // 获取重复获取读锁的次数
    private int getReadAccessCount(Thread callingThread) {
        return readingThreads.getOrDefault(callingThread, 0);
    }

    // 如果当前线程已经获取了读锁（说明没有线程获得写锁），那么即使有写锁请求，也可以获取读锁
    // 如果当前线程之前没有获得任何锁，其它线程没有获得写锁或者请求获得写锁，则可以获得读锁
    // 如果当前线程已经有了写锁，那么不可以获取读锁，这段是我自己加的，都已经获取写锁了，还要啥读锁。。。
    private boolean canGrantReadAccess(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        // 有线程获得写锁
        if (writers > 0) return false;
        // 如果当前线程已经获得了读锁则可以获得读锁，这个要在 writeRequests 前判断
        if (isReader(callingThread)) return true;
        // 如果上述两个判断都没通过，才需要判断 writeRequest 数量
        if (writeRequests > 0) return false;
        // 都没判断通过，说明没有人获得写锁，也没有写锁请求，自己也没有获得读锁
        return true;
    }

    // 判断当前线程是否是reader
    private boolean isReader(Thread callingThread) {
        return readingThreads.containsKey(callingThread);
    }
}
