package com.jetbone.design.patterns.behavioral.command;

/**
 * Created by Chris on 2019/9/3
 */
public class Video {
    private String name;

    public Video(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void open() {
        System.out.println("open video");
    }

    public void close() {
        System.out.println("close video");
    }
}
