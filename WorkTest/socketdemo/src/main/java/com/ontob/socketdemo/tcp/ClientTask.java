package com.ontob.socketdemo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 读客户端发来的信息，并发送信息给其他客户端
 */
public class ClientTask extends Thread implements MsgPool.MsgComingListener {
    InputStream mInputStrea;
    OutputStream mOutputStream;
    Socket mSocket;

    public ClientTask(Socket socket) {
        mSocket = socket;
        try {
            mInputStrea = mSocket.getInputStream();
            mOutputStream = mSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        super.run();
        BufferedReader br = new BufferedReader(new InputStreamReader(mInputStrea));
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.print("read:" + line + "\n");
                //转发消息至其他client
                MsgPool.getsInstance().sendMsg(mSocket.getPort()+":"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMsgComing(String msg) {
        try {
            mOutputStream.write(msg.getBytes());
            mOutputStream.write("\n".getBytes());
            mOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
