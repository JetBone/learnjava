package com.jetbone.others.concurrency.fairlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2020/4/28
 */
public class FairLock {

    private boolean isLocked = false;
    private Thread lockingThread = null;
    // 相当于线程等待池，进入 waiting 的线程都将存放到这个 list 里
    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException {
        // 这里意味着每一个进来的线程都会创建自己的 QueueObject 实例
        QueueObject queueObject = new QueueObject();
        // 先假定自己锁定了当前资源
        boolean isLockedForThisThread = true;
        // 这里使用 synchronized，保证 list 操作是单线程的，每一个进来的线程都先进入等待池
        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        // 以上，完成了初步工作，
        // 接下来，我们需要判断如果是第一个进来的线程，那我们认为他是真的需要执行的，所以要从等待池当中移除
        // 其它在等待池当中的线程应该全部进入 waiting 状态

        // 这里的 isLockedForThisThread 不能完全代表其字面意思
        while (isLockedForThisThread) {
            // 首先判断是否是真的需要执行的线程
            // 当isLocked 等于 false 的时候，说明目前没有任何一个线程获得锁，那么只有 waitingThreads 里的第一个元素才能获得锁
            // 当 isLocked 等于 true 的时候，说明已经有线程拿到锁了，那么将进入 waiting 状态
            synchronized (this) {
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
            }
            if (!isLockedForThisThread) {
                isLocked = true;
                waitingThreads.remove(queueObject);
                lockingThread = Thread.currentThread();
                // 不需要进行之后的 wait 操作，直接返回
                return;
            }
            try{
                // queueObject 每一个线程都有个单独自己的
                // 如果要让线程进入 waiting 状态，由于我们使用的是自定义的 lock，没有 synchronized，也就是说我们没有传统的 wait方式调用
                // 那么这里的 queueObject的 wait 起到了 wait 操作，进入 doWait 可以看到调用了以自己为monitorObject 的 wait 方法
                // 相当于执行这个方法的线程被 block 了，也就是 waiting 状态，效果是一样的
                // 另外，每一个线程都拥有自己 queueObject，当调用 notify 的时候，百分百唤醒的是自己，没有其它线程来竞争
                queueObject.doWait();
            }catch(InterruptedException e){
                // 做一个容错，如果发生了异常，则需要将这个锁从队列当中移除
                synchronized(this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void unlock() {
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked  = false;
        lockingThread = null;
        // 如果线程等待池当中已经没有等待的线程了，那也没必要 notify 了，否则 notify 队列当中的第一个元素
        if(waitingThreads.size() > 0){
            waitingThreads.get(0).doNotify();
        }
    }
}
