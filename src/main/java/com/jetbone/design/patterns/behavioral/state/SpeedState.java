package com.jetbone.design.patterns.behavioral.state;

/**
 * Created by Chris on 2019/9/10
 */
public class SpeedState extends VideoState {
    @Override
    public void play() {
        System.out.println("speed state can not play the video");
    }

    @Override
    public void pause() {
        System.out.println("pause the video");
        super.videoContext.setVideoState(VideoContext.PAUSE_STATE);
    }

    @Override
    public void stop() {
        System.out.println("stop the video");
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }

    @Override
    public void speed() {
        System.out.println("speed state can not speed the video");
    }
}
