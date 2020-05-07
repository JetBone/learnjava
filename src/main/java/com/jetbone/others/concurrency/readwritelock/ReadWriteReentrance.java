package com.jetbone.others.concurrency.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2020/5/7
 *
 * 一个线程在已经获得读锁的前提下获取写锁
 */
public class ReadWriteReentrance {

    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while(! canGrantWriteAccess(callingThread)){
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        // 新增一条判断
        if (isOnlyReader(callingThread)) return true;

        if (hasReader()) return false;
        if (writingThread == null) return true;
        if (writingThread == callingThread) return true;
        return false;
    }

    // 判断是否有读线程
    private boolean hasReader() {
        return readingThreads.size() > 0;
    }

    // 新增判断条件
    // 只有当前只有一个线程在读，并且还是当前线程的情况下，才能获得写锁
    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.containsKey(callingThread);
    }

}
