package com.ontob.socketdemo.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    ServerSocket mServerSocket = null;

    public void start() {
        try {
            mServerSocket = new ServerSocket(9090);
            MsgPool.getsInstance().start();//启动
            while (true) {
                Socket mSocket = mServerSocket.accept();
                System.out.print("iip:" + mSocket.getInetAddress().getHostAddress() + ",port:" + mSocket.getPort() + "is onlin......\n");
                ClientTask clientTask = new ClientTask(mSocket);
                MsgPool.getsInstance().addMsgComingListener(clientTask);
                clientTask.start();
            }
            //InputStream mInputStrea = mSocket.getInputStream();
           // OutputStream mOutputStream = mSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new TcpServer().start();
    }
}
