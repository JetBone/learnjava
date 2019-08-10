package com.jetbone.design.patterns.creational.abstractfactory;

/**
 * Created by Chris on 2019-08-01 22:51.
 */
public class Test {
    public static void main(String[] args) {

        // 抽象工厂其实只是一种扩展
        // 简单工厂是提供一种类型的创建
        // 工厂抽象方法是提供一种类型的创建，只不过推迟到实现类里
        // 抽象工厂只是将多个类型看成一个大的类型，提供这一系列类型的创建
        // 本质上三者没有什么区别，只不过是抽象程度的高低不同，抽象程度越高，扩展度也会随之增加
        // 当然随着抽象程度的增加，类的数量也会增加
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }
}
