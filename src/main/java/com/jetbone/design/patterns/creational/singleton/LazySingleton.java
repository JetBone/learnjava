package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 17:02.
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    /**
     * private 构造器，单例模式
     */
    private LazySingleton () {
    }

    /**
     * 虽然保证了只有个LazySingleton实例，但是这个是线程不安全的
     * 如果同时有两个线程调用这个方法，很可能产生两个实例
     * @return singleton instance
     */
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }

    /**
     * 该方法是上述方法的演进版本
     * 使用synchronized修饰后，保证了不会出现两个线程同时条用的情况
     * 但是使用synchronized修饰的是static方法，锁的是class级别的锁，而不是实例锁
     * 会导致每一次调用该方法，不管lazySingleton是否已经被实例化，都是要进行一次加锁和解锁的操作
     * @return singleton instance
     */
    public synchronized static LazySingleton getInstance2() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }

    /**
     * 于上述方法一样，虽然加锁的位置不一样，但是最终效果一样，每次调用还是会加锁
     * 只是上述方法的另外一种写法
     * @return
     */
    public static LazySingleton getInstance3() {
        synchronized (LazySingleton.class) {
            if (lazySingleton == null) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }

}
