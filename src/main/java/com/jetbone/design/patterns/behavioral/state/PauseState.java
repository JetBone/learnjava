package com.jetbone.design.patterns.behavioral.state;

/**
 * Created by Chris on 2019/9/10
 */
public class PauseState extends VideoState {
    @Override
    public void play() {
        System.out.println("start to play video");
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void pause() {
        System.out.println("already pause video");
    }

    @Override
    public void stop() {
        System.out.println("stop the video");
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }

    @Override
    public void speed() {
        System.out.println("pause state can not speed the video");
    }
}
