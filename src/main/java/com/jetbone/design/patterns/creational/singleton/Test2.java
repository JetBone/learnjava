package com.jetbone.design.patterns.creational.singleton;

import java.io.*;

/**
 * Created by Chris on 2019-08-13
 */
public class Test2 {
    public static void main(String[] args) throws Exception {

        // 这里做个一个操作，让HungrySingleton实现Serializable接口，使得该类可序列化
        // 使用序列化和反序列化操作，最终得到的newInstance和instance是两个不同的实例
        // 使用序列化和反序列化最终破坏了单例模式
        // 序列化和反序列化内部原理是调用的反射，所以某种意义上，应该是反射破坏了单例模式
        //
        // 在发现序列化和反序列化破坏了单例模式后，可以跟进ObjectInputStream的源码，
        // 根据源码可以发现，它先使用反射创建一个实例，
        // 然后在目标类中寻找一个readResolve方法，找到了就调用这个方法，覆盖之前创建的实例
        // 找不到就返回之前创建的新的实例
        // 所以我们在HungrySingleton类中申明了readResolve方法，并返回它本身创建的单例
        // 使用readResolve确实解决了序列化和反序列化中的破坏单例情况
        HungrySingleton instance = HungrySingleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hungry_instance"));
        // oos把对象写到一个名字叫做hungry_instance的文件里了，运行该方法后，项目根路径下出现了一个同名文件
        oos.writeObject(instance);
        // 读取刚刚创建的文件，从文件中读取序列化的类
        File file = new File("hungry_instance");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 可以读出单例对象，说明写入文件的信息除了类同时还有单例对象的信息
        HungrySingleton newInstance = (HungrySingleton) ois.readObject();
        System.out.println(instance);
        System.out.println(newInstance);
        System.out.println(instance == newInstance);

    }
}
