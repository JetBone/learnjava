package com.jetbone.design.patterns.creational.builder;

/**
 * Created by Chris on 2019-08-11 09:48.
 */
public class ActualCourseBuilder implements CourseBuilder {

    private Course course = new Course();

    @Override
    public void buildName(String name) {
        this.course.setName(name);
    }

    @Override
    public void buildVideo(String video) {
        this.course.setVideo(video);
    }

    @Override
    public void buildArticle(String article) {
        this.course.setArticle(article);
    }

    @Override
    public Course buildCourse() {
        return course;
    }
}
