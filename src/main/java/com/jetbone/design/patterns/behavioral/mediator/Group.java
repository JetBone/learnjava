package com.jetbone.design.patterns.behavioral.mediator;

/**
 * Created by Chris on 2019/9/3
 */
public class Group {
    public static void sendMessage(User user, String message) {
        System.out.println("user: " + user.getName() + " send group message: " + message);
    }
}
