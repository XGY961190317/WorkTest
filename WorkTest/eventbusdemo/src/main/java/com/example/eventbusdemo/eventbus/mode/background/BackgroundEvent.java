package com.example.eventbusdemo.eventbus.mode.background;

public class BackgroundEvent {
    public String threadInfo;

    public BackgroundEvent(String threadInfo) {
        this.threadInfo = threadInfo;
    }
}
