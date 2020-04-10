package com.jetbone.others.design.patterns.behavioral.observer;

/**
 * Created by Chris on 2019/9/2
 *
 * 评论
 *
 */
public class Comment {
    private User user;
    private String content;

    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
