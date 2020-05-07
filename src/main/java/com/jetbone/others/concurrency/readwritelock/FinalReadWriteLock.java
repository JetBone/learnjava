package com.jetbone.others.concurrency.readwritelock;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2020/5/7
 */
public class FinalReadWriteLock {

    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    private int writeAccesses = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (isWriter(callingThread)) return true;
        if (isReader(callingThread)) return true;
        if (writingThread != null) return false;
        if (writeRequests > 0) return false;
        return true;
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException("Calling Thread does not hold a read lock on this ReadWriteLock");
        }

        if (getReadAccessCount(callingThread) == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, getReadAccessCount(callingThread) - 1);
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) return true;
        if (readingThreads.size() > 0) return false;
        if (writingThread == null) return true;
        if (isWriter(callingThread)) return true;
        return false;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        if(!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling Thread does not hold the write lock on this ReadWriteLock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }


    private boolean isReader(Thread callingThread) {
        return readingThreads.containsKey(callingThread);
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.containsKey(callingThread);
    }

    private int getReadAccessCount(Thread callingThread) {
        return readingThreads.getOrDefault(callingThread, 0);
    }


}
