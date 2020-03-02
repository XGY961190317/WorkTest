package com.ontob.socketdemo.udp.biz;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UdpClientBiz {
    private String mServiceIp = "192.168.31.10";
    private int mServicePort = 7777;
    private DatagramSocket mSocket;
    private InetAddress mInetAddress;
    private Scanner mScanner;

    public UdpClientBiz() {
        try {
            try {
                mInetAddress = InetAddress.getByName(mServiceIp);
                mSocket = new DatagramSocket();
                mScanner = new Scanner(System.in);
                mScanner.useDelimiter("\n");

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(final String sendMsg, final OnMsgReturnedListener onMsgReturnedListener) {
        //zaiUI线程回调
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //先发送
                   // String clientMsg = mScanner.next();
                    byte[] clientByte = sendMsg.getBytes();
                    //准备DatagramPacket，发送给客户端
                    DatagramPacket clientPacket = new DatagramPacket(clientByte, clientByte.length, mInetAddress, mServicePort);
                    mSocket.send(clientPacket);

                    //接受服务端的数据
                    byte[] buf = new byte[1024];
                    DatagramPacket serverPacket = new DatagramPacket(buf, buf.length);//准备接受对象，接受服务端传回来的数据
                    mSocket.receive(serverPacket);
                    final String serverMsg = new String(serverPacket.getData(), 0, serverPacket.getLength());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onMsgReturnedListener.onMsgReturned(serverMsg);
                        }
                    });

                } catch (final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onMsgReturnedListener.onError(e);
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public interface OnMsgReturnedListener {
        void onMsgReturned(String returnMsg);

        void onError(Exception ex);
    }

    public void onDestroy() {
        if (mSocket != null) {
            mSocket.close();
        }

    }
    /**
     * public void start() {
     * while (true) {
     * try {
     * //先发送
     * String clientMsg = mScanner.next();
     * byte[] clientByte = clientMsg.getBytes();
     * //准备DatagramPacket，发送给客户端
     * DatagramPacket clientPacket = new DatagramPacket(clientByte, clientByte.length, mInetAddress, mServicePort);
     * mSocket.send(clientPacket);
     * //接受服务端的数据
     * byte[] buf = new byte[1024];
     * DatagramPacket servicePacket = new DatagramPacket(buf, buf.length);//准备接受对象，接受服务端传回来的数据
     * mSocket.receive(servicePacket);
     * System.out.print("服务端的:address=" + servicePacket.getAddress() + ",端口=" + servicePacket.getPort() + ",信息内容=" + new String(servicePacket.getData(), 0, servicePacket.getLength()));
     * } catch (IOException e) {
     * <p>
     * e.printStackTrace();
     * }
     * }
     * }
     * <p>
     * <p>
     *      **/

}
