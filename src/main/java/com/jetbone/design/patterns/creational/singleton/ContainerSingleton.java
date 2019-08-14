package com.jetbone.design.patterns.creational.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019-08-14
 */
public class ContainerSingleton {

    // 使用一个Map来装单例模式对象，适用于管理多个单例对象
    // 只要是运行时去维护单例对象，就会出现多线程问题，可能后一个覆盖前一个，也可能返回两个不同的对象
    // 使用HashTable确实可以解决多线程问题，但是频繁的加锁解锁会早场大量的资源浪费
    // 使用ConcurrentMap并不能完全解决多线程问题
    // 所以容器单例模式是需要考虑实际业务场景来决定是否使用的
    private Map<String, Object> singletonMap = new HashMap<>();

    private ContainerSingleton() {
    }

    public void setInstance(String name, Object instance) {
        if (!singletonMap.containsKey(name)) {
            singletonMap.put(name, instance);
        }
    }

    public Object getInstance(String name) {
        return singletonMap.get(name);
    }
}
