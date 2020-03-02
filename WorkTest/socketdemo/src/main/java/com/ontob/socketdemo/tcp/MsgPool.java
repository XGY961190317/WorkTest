package com.ontob.socketdemo.tcp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class MsgPool {
    public MsgPool() {
    }

    private static MsgPool sInstance = new MsgPool();
    private LinkedBlockingQueue<String> mQueue = new LinkedBlockingQueue<>();//队列

    public static MsgPool getsInstance() {
        return sInstance;
    }

public void start(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        String msg = mQueue.take();
                        notifyMsgcoming(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
}


    public void sendMsg (String msg){
        try {
            mQueue.put(msg);//将消息添加到队列
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 观察者模式
     *
     */

    private List<MsgComingListener>mListeners = new ArrayList<>();
    public void addMsgComingListener(MsgComingListener listener){
        mListeners.add(listener);
    }
    private void notifyMsgcoming(String msg) {
        for(MsgComingListener listener:mListeners){
            listener.onMsgComing(msg);
        }
    }

    public  interface MsgComingListener{
        void onMsgComing(String msg);
    }
}
