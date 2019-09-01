package com.jetbone.design.patterns.behavioral.templatemethod;

/**
 * Created by Chris on 2019/9/1
 */
public class Test {
    public static void main(String[] args) {
        Course javaCourse = new JavaCourse();
        javaCourse.buildCourse();
        System.out.println("------------------------");
        Course pythonCourse1 = new PythonCourse(true);
        pythonCourse1.buildCourse();
        System.out.println("------------------------");
        Course pythonCourse2 = new PythonCourse(false);
        pythonCourse2.buildCourse();
    }
}
