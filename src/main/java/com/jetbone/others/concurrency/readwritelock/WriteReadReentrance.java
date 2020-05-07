package com.jetbone.others.concurrency.readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2020/5/7
 *
 * 在当前线程已经有写锁的情况下获取读锁
 */
public class WriteReadReentrance {

    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    private int writeAccess = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    // 需要修改的方法，其它方法不需要改动，所以这个类里面没有加
    private boolean canGrantReadAccess(Thread callingThread){
        // 新增的判断条件，是否已经获取了写锁
        if (isWriter(callingThread)) return true;

        if (writingThread != null) return false;
        if (isReader(callingThread)) return true;
        if (writeRequests > 0) return false;
        return true;
    }

    // 判断当前线程是否是reader
    private boolean isReader(Thread callingThread) {
        return readingThreads.containsKey(callingThread);
    }

    // 判断当前线程是否是writer
    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }



}
