package com.jetbone.others.thread;

/**
 * Created by Chris on 2020/2/28
 */
public class DemoLock2 {

    public synchronized void print1() throws InterruptedException {
        System.out.println("demoLock2 print1 start");
        Thread.sleep(10 * 1000L);
        System.out.println("print1 end");

    }

    public synchronized void print2() throws InterruptedException {
        System.out.println("demoLock2 print2 start");
        Thread.sleep(10 * 1000L);
        System.out.println("print2 end");
    }

}
