package com.jetbone.others.design.patterns.creational.builder;

/**
 * Created by Chris on 2019-08-11 09:53.
 */
public class Test {
    public static void main(String[] args) {
        Coach coach = new Coach();
        CourseBuilder courseBuilder = new ActualCourseBuilder();
        coach.setCourseBuilder(courseBuilder);
        coach.buildCourse("Java", "Java Video", "Java Article");
    }
}
