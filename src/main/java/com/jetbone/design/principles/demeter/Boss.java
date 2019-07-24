package com.jetbone.design.principles.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019-07-23 22:11.
 */
public class Boss {

    // 该方法不符合demeter原则
    // BOSS不需要知道TeamLeader具体是怎么统计course数量的，只需要知道最终结果即可
    public void checkCourseNum(TeamLeader teamLeader) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courses.add(new Course());
        }
        teamLeader.checkCourseNum(courses);
    }
}
