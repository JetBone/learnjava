package com.jetbone.others.design.patterns.creational.builder.v2;

/**
 * Created by Chris on 2019-08-11 10:09.
 */
public class Test {
    public static void main(String[] args) {

        // 进阶版本的建造者，是放在Course内部的内部类
        // 进阶版本使用的是链式调用的方式
        // 链式调用可以很好的选择构造哪些组件，并且顺序可以打乱
        // 链式调用可以防止一些由于构造参数过多而导致的混乱或者错误
        // 感觉建造者链式调用的建造者模式就是一个"动态构造器"，可以按需构造参数，而不是将所有可能的方式都写一个构造方法
        // 一些其他想法
        // 1. CourseBuilder是static，但是内部方法不是，所以必须要new一个CourseBuilder才能进行建造
        // 2. CourseBuilder是static是为了在没有Course实例的情况下进行引用，那么为什么其内部的方法和参数不都设置为static呢？
        // 3. 应该是由于在最终build()方法中，需要调用course的构造方法，构造方法里面是对CourseBuilder实例的引用，所以必须要创建一个CourseBuilder实例
        Course course = new Course.CourseBuilder()
                .buildName("Java")
                .buildVideo("Java Video")
                .buildArticle("Java Article")
                .build();
    }
}
