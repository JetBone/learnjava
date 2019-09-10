package com.jetbone.design.patterns.behavioral.state;

/**
 * Created by Chris on 2019/9/10
 */
public abstract class VideoState {

    protected VideoContext videoContext;

    public VideoContext getVideoContext() {
        return videoContext;
    }

    protected void setVideoContext(VideoContext videoContext) {
        this.videoContext = videoContext;
    }

    public abstract void play();
    public abstract void pause();
    public abstract void stop();
    public abstract void speed();
}
