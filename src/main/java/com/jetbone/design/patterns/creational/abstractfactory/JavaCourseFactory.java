package com.jetbone.design.patterns.creational.abstractfactory;

/**
 * Created by Chris on 2019-08-10 12:27.
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
