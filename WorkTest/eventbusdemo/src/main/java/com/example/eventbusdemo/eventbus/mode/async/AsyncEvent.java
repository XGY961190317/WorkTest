package com.example.eventbusdemo.eventbus.mode.async;

public class AsyncEvent {
    public String threadInfo;

    public AsyncEvent(String threadInfo) {
        this.threadInfo = threadInfo;
    }
}
