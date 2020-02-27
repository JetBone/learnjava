package com.jetbone.thread;

/**
 * Created by Chris on 2020/2/27
 */
public class ThreadTest {

    public static void main(String[] args) {

        DemoLock demoLock = new DemoLock();

        Thread thread1 = new Thread(new Thread1(demoLock));
        Thread thread2 = new Thread(new Thread2(demoLock));

        thread1.start();
        thread2.start();

    }

}
