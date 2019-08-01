package com.jetbone.design.patterns.creational.simplefactory;

/**
 * Created by Chris on 2019-08-01 22:51.
 */
public class Test {
    public static void main(String[] args) {

        // 这里在应用层实例化具体类，调用类的方法
        // 如果应用层与业务层不再同一个包下，则需要类就需要import
        Video video1 = new JavaVideo();
        Video video2 = new PythonVideo();
        video1.produce();
        video2.produce();

        VideoFactory videoFactory = new VideoFactory();
        // 使用工厂类获取需要的课程实例
        // 缺点：新增课程类之后，需要修改工厂方法
        Video video3 = videoFactory.getVideo("java");
        Video video4 = videoFactory.getVideo("python");
        video3.produce();
        video4.produce();

        // 使用反射来获取对应的实例
        // 优点：新增课程类之后，不需要再修改工厂方法
        // 缺点：需要引包
        Video video5 = videoFactory.getVideo(JavaVideo.class);
        Video video6 = videoFactory.getVideo(PythonVideo.class);
        video5.produce();
        video6.produce();
    }
}
