package com.example.eventbusdemo.eventbus.mode.mainordered;

public class MainOrDeredEvent {
    public String threadInfo;

    public MainOrDeredEvent(String threadInfo) {
        this.threadInfo = threadInfo;
    }
}
