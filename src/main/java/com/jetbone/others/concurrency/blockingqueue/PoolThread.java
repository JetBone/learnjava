package com.jetbone.others.concurrency.blockingqueue;


/**
 * Created by Chris on 2020/5/8
 */
public class PoolThread extends Thread {

    // 共享变量，用于存放task
    private BlockingQueue taskQueue = null;

    private boolean isStopped = false;

    public PoolThread(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        // 构造一个"死循环"，保证线程永远不会被关闭，当然，如果没有task的时候，会进入 waiting 状态
        while (!isStopped()) {
            try {
                // 如果 queue 里是空的，就会进入 waiting 状态
                Runnable task = (Runnable) taskQueue.dequeue();
                // 注意，这里调用的 run() 方法，也就是说这个 Runnable 其实并没有什么特别的意义（甚至可以替换成其它自定义的类或者接口），只是用来提供一个方便统一调用的方法
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 只有外部的线程池调用这个方法，当前线程才会被关闭
    public synchronized void doStop() {
        isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
