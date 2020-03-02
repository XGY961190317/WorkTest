package com.ontob.socketdemo.tcp.tcpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private Scanner mScanner;

    public TcpClient(){
        mScanner = new Scanner(System.in);
        mScanner.useDelimiter("\n");//换行
    }
    public void start() {
        try {
            Socket socket = new Socket("192.168.31.10",9090);
            final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//读写行
            BufferedWriter bw  =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new Thread(){
                @Override
                public void run() {
                    try {
                        String line = null;
                        while ((line=br.readLine())!=null){
                            System.out.print("server:"+line+"\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            while (true){
               String msg =  mScanner.next();
                bw.write(msg);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new TcpClient().start();
    }

}
