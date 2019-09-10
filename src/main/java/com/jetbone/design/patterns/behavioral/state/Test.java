package com.jetbone.design.patterns.behavioral.state;

/**
 * Created by Chris on 2019/9/10
 */
public class Test {
    public static void main(String[] args) {
        VideoContext videoContext = new VideoContext();
        videoContext.play();
        videoContext.pause();
        videoContext.pause();
        videoContext.speed();
        videoContext.play();
        videoContext.speed();
        videoContext.pause();
        videoContext.stop();
        videoContext.pause();
        videoContext.speed();
        videoContext.play();
        videoContext.stop();
    }
}
