package com.jetbone.others.design.principles.singleresponsibility;

/**
 * Created by Chris on 2019-07-23 21:22.
 * 该接口负责两个职责
 * 1.获取course信息
 * 2.管理course
 * 根据单一职责原则，应该定义两个接口分别声明自己的方法
 */
public interface ICourse {

    // course info
    String getCourseName();
    String getCoursePrice();

    // manage course
    void studyCourse();
    void refundCourse();
}
