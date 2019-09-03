package com.jetbone.design.patterns.behavioral.mediator;

/**
 * Created by Chris on 2019/9/3
 */
public class Test {
    public static void main(String[] args) {
        User user1 = new User("Tom");
        User user2 = new User("Jerry");

        // 用户将发送消息的功能交由中介者Group来处理
        user1.sendMessage("Hello Jerry");
        user2.sendMessage("Hello Tom");

        // 这里的Group就像是代码中常见的Client类
    }
}
