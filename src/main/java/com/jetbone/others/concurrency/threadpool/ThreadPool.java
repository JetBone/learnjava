package com.jetbone.others.concurrency.threadpool;

import com.jetbone.others.concurrency.blockingqueue.BlockingQueue;
import com.jetbone.others.concurrency.blockingqueue.PoolThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2020/5/8
 */
public class ThreadPool {
    // 共享变量，与所有线程池共享，存放 task
    private BlockingQueue taskQueue = null;
    // 用于存放所有被 Thread Pool 管理的线程
    private List<PoolThread> poolThreads = new ArrayList<>();
    private boolean isStopped = false;

    /**
     * 线程池构造器
     * @param noOfThreads 启动的线程数量
     * @param maxNoOfTasks 最多存放多少个 task
     */
    public ThreadPool(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for (int i = 0; i < noOfThreads; i++) {
            // 构造线程池线程，传入共享变量，存入 List 当中
            poolThreads.add(new PoolThread(taskQueue));
        }
        // 启动线程
        for (PoolThread thread : poolThreads) {
            // 最一开始因为 taskQueue 是空的，所有线程都会进入 waiting 状态
            thread.start();
        }
    }

    // 提交 task 到线程池，使用 synchronized 修饰
    public synchronized void execute(Runnable task) throws InterruptedException {
        // 先判断线程是是否已经被停止
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        // 将 task 放入 queue
        taskQueue.enqueue(task);
    }

    // 使用 synchronized 修饰，当调用stop 方法的时候，将不允许有新的任务提交，或者如果有正在提交的任务，则只有当任务提交成功之后，才能执行 stop 方法
    public synchronized void stop() {
        this.isStopped = true;
        for (PoolThread thread : poolThreads) {
            thread.doStop();
        }
    }


}
