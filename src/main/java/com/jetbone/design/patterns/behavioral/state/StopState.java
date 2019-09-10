package com.jetbone.design.patterns.behavioral.state;

/**
 * Created by Chris on 2019/9/10
 */
public class StopState extends VideoState {
    @Override
    public void play() {
        System.out.println("start to play the video");
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void pause() {
        System.out.println("stop state can not pause the video");
    }

    @Override
    public void stop() {
        System.out.println("stop state can not stop the video");
    }

    @Override
    public void speed() {
        System.out.println("stop state can not speed the video");
    }
}
