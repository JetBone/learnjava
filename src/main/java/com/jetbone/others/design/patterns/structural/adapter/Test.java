package com.jetbone.others.design.patterns.structural.adapter;

/**
 * Created by Chris on 2019/8/27
 */
public class Test {
    public static void main(String[] args) {

        // 这是标准的开发模式，针对接口编写实现类并实现具体的方法
        Target target = new ConcreteTarget();
        target.request();

        // 这个是在开发维护当中，发现了一个类的方法，其实是符合Target的request方法的
        // 但是对于已经开发好的代码，我们不可能去修改让Adapted类实现Target接口
        // 也不会去修改Adapted类中的方法名称
        // 所以介入第三方类，即适配器
        // 就像是一个代理一样(当然和代理模式中的代理不一样)
        // 看起来像是让Adapted实现了Target接口(实际上没有)

        // 类适配器 继承Adapted类，实现Target接口
        Target target1 = new ClassAdapter();
        target1.request();

        // 对象适配器 注入Adapted实例，实现Target接口
        Target target2 = new ObjectAdapter();
        target2.request();

        // 在继承和组合中，优先使用组合，所以优先使用对象适配器模式
    }
}
