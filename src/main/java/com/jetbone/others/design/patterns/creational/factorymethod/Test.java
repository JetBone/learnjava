package com.jetbone.others.design.patterns.creational.factorymethod;

/**
 * Created by Chris on 2019-08-01 22:51.
 */
public class Test {
    public static void main(String[] args) {

        // 将工厂方法抽象，交由子类实现
        // 可以提高扩展性，如果需要新增Video种类，无需修改工厂类
        // 只需要新增工厂类和对应的产品类即可完成扩展
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }
}
