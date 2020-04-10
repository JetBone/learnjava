package com.jetbone.others.design.patterns.behavioral.state;


/**
 * Created by Chris on 2019/9/10
 */
public class VideoContext {


    private VideoState videoState;

    // 默认视频为停止状态
    {
        this.setVideoState(STOP_STATE);
    }

    public static final PlayState PLAY_STATE = new PlayState();
    public static final PauseState PAUSE_STATE = new PauseState();
    public static final StopState STOP_STATE = new StopState();
    public static final SpeedState SPEED_STATE = new SpeedState();

    public VideoState getVideoState() {
        return videoState;
    }

    public void setVideoState(VideoState videoState) {
        this.videoState = videoState;
        this.videoState.setVideoContext(this);
    }

    public void play() {
        this.videoState.play();
    }

    public void pause() {
        this.videoState.pause();
    }

    public void stop() {
        this.videoState.stop();
    }

    public void speed() {
        this.videoState.speed();
    }
}
