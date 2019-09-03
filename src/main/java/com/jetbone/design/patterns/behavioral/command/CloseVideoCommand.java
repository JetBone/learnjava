package com.jetbone.design.patterns.behavioral.command;

/**
 * Created by Chris on 2019/9/3
 */
public class CloseVideoCommand implements Command {

    private Video video;

    public CloseVideoCommand(Video video) {
        this.video = video;
    }

    @Override
    public void execute() {
        video.close();
    }
}
