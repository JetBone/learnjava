package com.jetbone.design.principles.demeter;

/**
 * Created by Chris on 2019-07-23 22:16.
 */
public class Test {
    public static void main(String[] args) {

        // 不符合demeter原则
        Boss boss = new Boss();
        boss.checkCourseNum(new TeamLeader());

        // 符合demeter原则
        NewBoss newBoss = new NewBoss();
        newBoss.checkCourseNum(new NewTeamLeader());
    }
}
