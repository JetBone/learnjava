package com.jetbone.design.principles.demeter;


/**
 * Created by Chris on 2019-07-23 22:17.
 */
public class NewBoss {
    // 该方法符合demeter原则
    // BOSS不需要知道TeamLeader具体是怎么统计course数量的，只需要知道最终结果即可
    public void checkCourseNum(NewTeamLeader newTeamLeader) {
        newTeamLeader.checkCourseNum();
    }
}
