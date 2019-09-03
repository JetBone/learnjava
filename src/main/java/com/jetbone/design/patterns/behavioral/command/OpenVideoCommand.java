package com.jetbone.design.patterns.behavioral.command;

/**
 * Created by Chris on 2019/9/3
 */
public class OpenVideoCommand implements Command {

    private Video video;

    public OpenVideoCommand(Video video) {
        this.video = video;
    }

    @Override
    public void execute() {
        video.open();
    }
}
