package com.jetbone.others.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 17:07.
 */
public class Test {
    public static void main(String[] args) {
        // 这个是很简单的单线程调用
        LazySingleton lazySingleton = LazySingleton.getInstance();

        // 这里单纯的从console上看，像是返回的是同一个对象其实不然
        // 如果使用线程debug，手动的控制两个线程进行顺序，会造成多个结果
        // 1. 两个线程均进入了if判断，但是两个线程都是在实例化LazySingleton之后才返回，那么后一个实例化的会覆盖前一个实例化的对象
        //    造成了看起来是同一个对象的错觉，实际上实例化了两边
        // 2. 如果手动控制让其中一个线程在另外一个线程实例化前返回，那么就会出现返回两个不同的实例的情况
        // 最终就是无法控制最终的返回结果是什么样子
        Thread thread1 = new Thread(new ThreadTest());
        Thread thread2 = new Thread(new ThreadTest());
        thread1.start();
        thread2.start();

        // 使用volatile和synchronized解决了懒汉加载的多线程重排序问题
        Thread thread3 = new Thread(new ThreadTest2());
        Thread thread4 = new Thread(new ThreadTest2());
        thread3.start();
        thread4.start();

        System.out.println("Program End.");
    }
}
