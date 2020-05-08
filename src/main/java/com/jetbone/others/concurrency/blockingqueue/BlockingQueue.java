package com.jetbone.others.concurrency.blockingqueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chris on 2020/4/7
 */
public class BlockingQueue {

    private List queue = new LinkedList();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(Object item) throws InterruptedException {
        // 如果 queue 已经达到了 limit 上限，则进入 waiting 状态
        while (queue.size() >= limit) {
            wait();
        }

        queue.add(item);

        // 如果之前有线程取数据但是因为是空所以进入 waiting 状态，需要被唤醒
        if (queue.size() == 1) {
            notifyAll();
        }
    }

    public synchronized Object dequeue() throws InterruptedException {
        // 如果 queue 是空的，则进入 waiting 状态
        while (queue.size() == 0) {
            wait();
        }

        // 如果之前有线程存数据但是因为 queue 满了所以进入 waiting 状态，需要被唤醒
        if (queue.size() == limit) {
            notifyAll();
        }

        return queue.remove(0);
    }
}
