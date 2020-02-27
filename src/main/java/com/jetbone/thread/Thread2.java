package com.jetbone.thread;


/**
 * Created by Chris on 2020/2/27
 */
public class Thread2 implements Runnable {

    private DemoLock demoLock;

    public Thread2(DemoLock demoLock) {
        this.demoLock = demoLock;
    }

    @Override
    public void run() {
        try {
            demoLock.print2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
