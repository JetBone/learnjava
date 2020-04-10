package com.jetbone.others.design.patterns.behavioral.templatemethod;

/**
 * Created by Chris on 2019/9/1
 */
public abstract class Course {

    /**
     * 使用protected修饰继承给子类
     * 使用final修饰不允许子类重写
     * 统一的算法方法
     */
    protected final void buildCourse() {
        System.out.println("build course start");
        buildPPT();
        buildVideo();
        if (isNeedNote()) {
            buildNote();
        }
        packageCourse();
        System.out.println("build course end");
    }

    final void buildPPT() {
        System.out.println("build course ppt");
    }

    final void buildVideo() {
        System.out.println("build course video");
    }

    final void buildNote() {
        System.out.println("build course note");
    }

    /**
     * 开放重写权限，子类根据需求进行重写
     * @return
     */
    protected boolean isNeedNote() {
        return false;
    }

    /**
     * 不同的课程有不同的打包方式
     * 交给子类去实现的方法
     */
    protected abstract void packageCourse();

}
