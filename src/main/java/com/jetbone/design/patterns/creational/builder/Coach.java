package com.jetbone.design.patterns.creational.builder;


/**
 * Created by Chris on 2019-08-11 09:51.
 */
public class Coach {
    private CourseBuilder courseBuilder;

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }

    public Course buildCourse(String name, String video, String article) {
        courseBuilder.buildName(name);
        courseBuilder.buildVideo(video);
        courseBuilder.buildArticle(article);

        return courseBuilder.buildCourse();
    }
}
