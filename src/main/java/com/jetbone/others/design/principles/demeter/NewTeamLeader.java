package com.jetbone.others.design.principles.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019-07-23 22:18.
 */
public class NewTeamLeader {
    public void checkCourseNum() {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courses.add(new Course());
        }
        System.out.println("Course Number is " + courses.size());
    }
}
