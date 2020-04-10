package com.jetbone.others.thread;

/**
 * Created by Chris on 2020/2/27
 */
public class Thread1 implements Runnable {

    private DemoLock1 demoLock1;

    public Thread1(DemoLock1 demoLock1) {
        this.demoLock1 = demoLock1;
    }

    @Override
    public void run() {
        try {
            demoLock1.print1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
