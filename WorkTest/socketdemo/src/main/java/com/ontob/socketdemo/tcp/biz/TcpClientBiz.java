package com.ontob.socketdemo.tcp.biz;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClientBiz {
    private String mServiceIp = "192.168.31.10";
    private int mServicePort = 9090;
    private InetAddress mInetAddress;
    private Socket mSocket;
    private  InputStream mMIs;
    private  OutputStream mMOs;

    public TcpClientBiz() {

            recevierMsg();

    }

    public void recevierMsg() {
        //zaiUI线程回调
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mInetAddress = InetAddress.getByName(mServiceIp);

                mSocket = new Socket("192.168.31.10", 9090);
                mMIs = mSocket.getInputStream();
                mMOs = mSocket.getOutputStream();
                receiverMsg(handler);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void receiverMsg(Handler handler) {
        BufferedReader br = new BufferedReader(new InputStreamReader(mMIs));
        String line;
        try {
            while((line=br.readLine())!=null){
                final String finalLine = line;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mOnMsgComingListener.onMsgReturned(finalLine);
                    }
                });
            }
        } catch (final IOException e) {
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mOnMsgComingListener.onError(e);
                }
            });
        }
    }

    public void sendMsg(final String sendMsg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(mMOs));
                    bw.write(sendMsg);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private OnMsgComingListener mOnMsgComingListener;

    public void setOnMsgComingListener(OnMsgComingListener listener) {
        mOnMsgComingListener = listener;
    }

    public interface OnMsgComingListener {
        void onMsgReturned(String returnMsg);

        void onError(Exception ex);
    }

    public void onDestroy() {
        if (mMIs!=null){
            try {
                mMIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mMOs!=null){
            try {
                mMOs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mSocket != null) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
