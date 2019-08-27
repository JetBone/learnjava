package com.jetbone.design.patterns.structural.adapter;

/**
 * Created by Chris on 2019/8/27
 * 对象适配器模式
 */
public class ObjectAdapter implements Target {

    // 这个应该是让Spring来注入，这里简单进行一下模拟
    private Adapted adapted = new Adapted();

    @Override
    public void request() {
        // 这里可以添加自己想添加的业务功能
        adapted.AdaptedRequest();
        // 这里可以添加自己想添加的业务功能
    }
}
