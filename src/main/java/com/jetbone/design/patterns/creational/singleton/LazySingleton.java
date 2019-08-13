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
        // 在私有化构造器里追加判断，可以防止使用反射再次创建新的实例
        // 这样的方式并不能完美的保证反射和获取的单例是同一个实例
        // 只能保证在获取实例的时候抛出异常，强行禁止破坏单例模式
        // 在懒加载中使用该判断，最终会有两种结果
        // 1.如果先用反射获取实例，然后在用自身静态方法获取单例，则会获取两个不同的实例，导致单例模式被破坏
        // 2.如果先用自身静态方法获取单例，然后用反射获取实例，则调用反射的时候会抛出异常，阻止破坏单例模式
        // 其原理是使用反射获取实例，并不会更新类中lazySingleton参数的指向，所以其仍然为null
        // 所以懒加载使用此判断不一定能完全阻止反射破坏单例模式
        // 饿汉式使用该方法判断可以阻止反射破坏单例模式，具体见HungrySingleton构造器注释
        if (lazySingleton != null) {
            throw new RuntimeException("单例模式禁止反射调用构造器");
        }
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
