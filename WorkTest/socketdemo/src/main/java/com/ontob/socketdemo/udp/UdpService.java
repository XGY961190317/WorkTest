package com.ontob.socketdemo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UdpService {
    private InetAddress mInetAddress;
    private int mPort = 7777;
    private DatagramSocket mSocket;
    Scanner mScanner;

    public UdpService() {
        try {
            mInetAddress = InetAddress.getLocalHost();
            mSocket = new DatagramSocket(mPort, mInetAddress);
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket receviePacket = new DatagramPacket(buf, buf.length);//获取客户端传过来的信息
                mSocket.receive(receviePacket);
                InetAddress address = receviePacket.getAddress();//湖区地址
                int port = receviePacket.getPort();//获取端口号
                String clientMsg = new String(receviePacket.getData(), 0, receviePacket.getLength());//获取内容信息
                System.out.print("address=" + address + ",port=" + port + ",msg:\n" + clientMsg);
                String returnMsg  = mScanner.next();//从键盘获取输入的字符
                byte[] returnMsgByte = returnMsg.getBytes();//将输入的字符转为byte
                DatagramPacket sendPacket = new DatagramPacket(returnMsgByte,returnMsgByte.length,receviePacket.getSocketAddress());//发送给客户端
                mSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new UdpService().start();
    }
}
