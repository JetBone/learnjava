package com.jetbone.others.thread;

/**
 * Created by Chris on 2020/2/27
 */
public class ThreadTest {

    public static void main(String[] args) {

        DemoLock1 demoLock1 = new DemoLock1();

        Thread thread1 = new Thread(new Thread1(demoLock1));
        Thread thread2 = new Thread(new Thread2(demoLock1));

        thread1.start();
        thread2.start();

    }

}
