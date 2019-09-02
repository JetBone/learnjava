package com.jetbone.design.patterns.behavioral.observer;

/**
 * Created by Chris on 2019/9/2
 */
public class Test {
    public static void main(String[] args) {
        Moment moment = Moment.of(new User("Tom"), "Hello Jerry");
        Comment comment = new Comment(new User("Jerry"), "Hello Tom");

        moment.addComments(comment);

    }
}
