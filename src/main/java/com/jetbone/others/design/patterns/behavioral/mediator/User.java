package com.jetbone.others.design.patterns.behavioral.mediator;

/**
 * Created by Chris on 2019/9/3
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 调用中介者进行消息发送
     * @param message 消息
     */
    public void sendMessage(String message) {
        Group.sendMessage(this, message);
    }
}
