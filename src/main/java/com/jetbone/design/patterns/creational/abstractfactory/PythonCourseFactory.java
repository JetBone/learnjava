package com.jetbone.design.patterns.creational.abstractfactory;

/**
 * Created by Chris on 2019-08-10 12:28.
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
