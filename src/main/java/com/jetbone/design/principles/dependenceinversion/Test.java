package com.jetbone.design.principles.dependenceinversion;

/**
 * Created by Chris on 2019-07-17 21:41.
 */
public class Test {

    public static void main(String[] args) {

        // 版本1
        // 当我们想新增学习方法的时候，需要在在Student1类中新增方法，同时在当前应用曾新增方法调用
        // student 与 test 耦合
        Student1 student1 = new Student1();
        student1.studyJava();
        student1.studyJavaScript();
        student1.studyPython();

        // 版本2
        // 将学习方法抽象一个接口，使用具体类来实现具体的学习方法
        // student 依赖接口而不依赖实现类，在应用层注入具体实现类来指定学习方法
        // student 方法注入 study
        // student 与 test 解藕
        Student2 student2 = new Student2();
        student2.study(new StudyJava());
        student2.study(new StudyJavaScript());
        student2.study(new StudyPython());

        // 版本3
        // 同上，使用构造器注入
        new Student3(new StudyJava()).study();
        new Student3(new StudyJavaScript()).study();
        new Student3(new StudyPython()).study();

        // 版本4
        // 同上，使用setter方法注入
        Student3 student3 = new Student3();
        student3.setStudy(new StudyJava());
        student3.study();
        student3.setStudy(new StudyJavaScript());
        student3.study();
        student3.setStudy(new StudyPython());
        student3.study();
    }
}
