package com.jetbone.design.patterns.structural.flyweight;

/**
 * Created by Chris on 2019/8/28
 */
public class Test {

    private static final String[] departments = {"A","B","C","D","E"};

    public static void main(String[] args) {

        // 可以看到在最初创建了部门经理之后的几次循环都会从缓存池当中寻找已经存在的部门经理
        // 避免了反复创建相同的对象
        // 享元模式的关键在于工厂中的MAP，所以会存在一些线程安全问题，具体需要根据业务情况分析
        // 缓存不一定要保存到工厂中，也可以放到类本身内部的一个static Map中
        // 享元模式的核心就是缓存现有的对象以便复用
        for (int i = 0; i < 10; i++) {
            String department = departments[(int) (Math.random() * departments.length)];
            Manager manager = (Manager) ManagerFactory.getManager(department);
            manager.report();
        }

    }
}
