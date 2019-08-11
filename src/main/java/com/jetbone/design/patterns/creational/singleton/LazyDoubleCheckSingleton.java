package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 18:33.
 */
public class LazyDoubleCheckSingleton {

    // 加上volatile修饰符，会禁止重排序
    // 用volatile修饰的共享变量，在编译器当中会出现一些操作
    // 一旦该变量出现变化，那么会导致其他CPU中缓存的该共享变量地址无需，需要重写获取，保证了一致性
    // 这里简单进行的描述一下，不一定准确，具体请了解volatile的用法
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    /**
     * private 构造器，单例模式
     */
    private LazyDoubleCheckSingleton () {
    }

    /**
     * 双重检查，好处是第一次检查不需要加锁，只有在第一次创建的是时候，if判断才会进去，然后加锁
     * 一旦创建成功，之后再调用该方法，都不会进行锁的操作，减少了加锁解锁的操作消耗
     * @return singleton instance
     */
    public static LazyDoubleCheckSingleton getInstance() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {

                    // 该行代码虽然只有一行，理论上上JVM做了三步操作
                    // 1. 分配内存空间给该对象
                    // 2. 在内存空间中初始化该对象
                    // 3. 设置lazyDoubleCheckSingleton指向刚刚分配的内存空间
                    // 上述是理论，实际操作中，JVM可能为了优化步骤，出现2和3重排序(也可能不排序)，最终执行顺序为1-3-2
                    // JVM是允许出现重排序的，详情请了解 intra-thread semantics 名词介绍
                    // 只要变量指向了内存空间，即使这个内存内的对象还没有真正初始化，那么也会认为该变量不等于null
                    // 这里造成的效果就是，假设第一个线程走的步骤为1-3-2，在执行到3时候，另外一个线程进行了null判断
                    // 结果认为该变量不等于null，那么就直接返回该对象，并进行调用，然而实际上该对象在内存中还没有进行初始化
                    // 那么调用的时候就会出现异常
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }

        return lazyDoubleCheckSingleton;
    }
}
