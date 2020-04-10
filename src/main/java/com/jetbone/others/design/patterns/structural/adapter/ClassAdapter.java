package com.jetbone.others.design.patterns.structural.adapter;

/**
 * Created by Chris on 2019/8/27
 * 类适配器模式
 */
public class ClassAdapter extends Adaptee implements Target {
    /**
     * 具体的实现方法调用了继承的，需要被适配的request方法
     * 类适配器模式
     */
    @Override
    public void request() {
        // 这里可以添加自己想添加的业务功能
        super.AdaptedRequest();
        // 这里可以添加自己想添加的业务功能
    }
}
