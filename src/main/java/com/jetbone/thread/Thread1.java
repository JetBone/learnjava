package com.jetbone.thread;

/**
 * Created by Chris on 2020/2/27
 */
public class Thread1 implements Runnable {

    private DemoLock demoLock;

    public Thread1(DemoLock demoLock) {
        this.demoLock = demoLock;
    }

    @Override
    public void run() {
        try {
            demoLock.print1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
