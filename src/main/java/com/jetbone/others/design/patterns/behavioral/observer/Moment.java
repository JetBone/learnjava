package com.jetbone.others.design.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Chris on 2019/9/2
 *
 * 朋友圈
 *
 */
public class Moment extends Observable {
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private String content;

    /**
     * 私有化构造器，只允许通过工厂方法新建实例
     * @param user
     * @param content
     */
    private Moment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    /**
     * 创建好朋友圈后，设置观察者
     * @param user
     * @param content
     * @return
     */
    public static Moment of(User user, String content) {
        Moment instance = new Moment(user, content);
        instance.addObserver(user);
        return instance;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment newComment) {
        comments.add(newComment);
        System.out.println("用户：" + newComment.getUser().getUserName()
                + " 在：" + this.user.getUserName()
                + " 的朋友圈发表了评论：" + newComment.getContent()
        );
        setChanged();
        notifyObservers(newComment);
    }

}
