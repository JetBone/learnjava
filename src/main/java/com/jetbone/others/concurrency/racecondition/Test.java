package com.jetbone.others.concurrency.racecondition;

/**
 * Created by Chris on 2020/4/27
 */
public class Test {

    public static void main(String[] args) {

        // shared resource
        Counter counter = new Counter();

        Runnable runnable = new Thread1(counter);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        // 多次调用debug之后，产生了各种不同的结果
        // 1，1
        // 2，2
        // 1，2
    }

}
