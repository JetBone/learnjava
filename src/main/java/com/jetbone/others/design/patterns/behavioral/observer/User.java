package com.jetbone.others.design.patterns.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Chris on 2019/9/2
 *
 * 用户
 *
 */
public class User implements Observer {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void update(Observable o, Object arg) {
        Moment moment = (Moment) o;
        Comment comment = (Comment) arg;

        System.out.println("用户：" + moment.getUser().getUserName()
                + " 在朋友圈发的消息：" + moment.getContent() + "\n"
                + " 收到一条由：" + comment.getUser().getUserName()
                + " 发送的一条评论：" + comment.getContent()
        );
    }
}
