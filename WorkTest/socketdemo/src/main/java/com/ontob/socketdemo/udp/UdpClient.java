package com.ontob.socketdemo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UdpClient {
    private String mServiceIp = "192.168.31.10";
    private int mServicePort = 7777;
    private DatagramSocket mSocket;
    private InetAddress mInetAddress;
    private Scanner mScanner;

    public UdpClient() {
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

    public void start() {
        while (true) {
            try {
                //先发送
                String clientMsg = mScanner.next();
                byte[] clientByte = clientMsg.getBytes();
                        //准备DatagramPacket，发送给客户端
                DatagramPacket clientPacket = new DatagramPacket(clientByte, clientByte.length, mInetAddress, mServicePort);
                mSocket.send(clientPacket);
                //接受服务端的数据
                byte [] buf = new byte[1024];
                DatagramPacket servicePacket = new DatagramPacket(buf,buf.length);//准备接受对象，接受服务端传回来的数据
                mSocket.receive(servicePacket);
                System.out.print("服务端的:address="+servicePacket.getAddress()+",端口="+servicePacket.getPort()+",信息内容="+new String(servicePacket.getData(),0,servicePacket.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new UdpClient().start();
    }
}
