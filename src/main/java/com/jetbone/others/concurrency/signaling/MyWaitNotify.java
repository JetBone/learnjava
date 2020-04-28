package com.jetbone.others.concurrency.signaling;

/**
 * Created by Chris on 2020/4/28
 */
public class MyWaitNotify {

    private final MonitorObject monitorObject = new MonitorObject();

    public void doWait() {
        synchronized (monitorObject) {
            try {
                monitorObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doNotify() {
        synchronized (monitorObject) {
            monitorObject.notify();
        }
    }
}
