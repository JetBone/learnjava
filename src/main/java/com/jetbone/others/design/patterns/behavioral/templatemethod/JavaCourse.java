package com.jetbone.others.design.patterns.behavioral.templatemethod;

/**
 * Created by Chris on 2019/9/1
 */
public class JavaCourse extends Course {

    @Override
    protected void packageCourse() {
        System.out.println("package java source code");
    }
}
