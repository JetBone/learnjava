package com.jetbone.design.patterns.creational.builder;

/**
 * Created by Chris on 2019-08-11 09:47.
 */
public interface CourseBuilder {
    void buildName(String name);
    void buildVideo(String video);
    void buildArticle(String article);
    Course buildCourse();
}
