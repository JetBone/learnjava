package com.jetbone.others.concurrency.signaling;

/**
 * Created by Chris on 2020/4/28
 */
public class MyWaitNotify2 {

    private final MonitorObject monitorObject = new MonitorObject();

    // 用来保存 notify 信号，防止信号丢失
    private boolean wasSignaled = false;

    public void doWait() {
        synchronized (monitorObject) {
            // 在做 wait 操作前，必须先判断是否有信号存在，如果存在，说明 notify 没有唤醒任何一个线程，则不能进行 wait 操作
            if (!wasSignaled) {
                try {
                    // do something
                    monitorObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 走到这里代表有线程从前面的 waiting 状态唤醒，需要更新一下信号状态，让之前的想要 wait 的线程进入 waiting 状态
            wasSignaled = false;
        }
    }

    public void doNotify() {
        synchronized (monitorObject) {
            // 如果调用了 notify 将 isSignaled 设置为 true
            wasSignaled = true;
            monitorObject.notify();
        }
    }
}
