package com.jetbone.design.principles.demeter;

import java.util.List;

/**
 * Created by Chris on 2019-07-23 22:12.
 */
public class TeamLeader {
    public void checkCourseNum(List<Course> courses) {
        System.out.println("Course Number is " + courses.size());
    }
}
