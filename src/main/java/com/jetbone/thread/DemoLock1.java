package com.jetbone.thread;

/**
 * Created by Chris on 2020/2/27
 */
public class DemoLock1 {

    public synchronized void print1() throws InterruptedException {
        System.out.println("demoLock1 print1 start");
        Thread.sleep(10 * 1000L);
        System.out.println("print1 end");

    }

    public synchronized void print2() throws InterruptedException {
        System.out.println("demoLock1 print2 start");
        Thread.sleep(10 * 1000L);
        System.out.println("print2 end");
    }

}
