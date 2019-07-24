package com.jetbone.design.principles.singleresponsibility;

/**
 * Created by Chris on 2019-07-23 21:24.
 * 只需要同时实现两个接口即可完成所有功能
 * 也可以分开实现，具有可扩展性
 */
public class CourseImpl implements ICourseInfo,ICourseManager {
    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public String getCoursePrice() {
        return null;
    }

    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }
}
