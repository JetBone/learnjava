package com.jetbone.others.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chris on 2020/1/2
 */
public class MySocketServer {

    public static void main(String[] args) {
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(8080);
                    while(true) {
                        Socket recv = serverSocket.accept();
                        InputStream input = new DataInputStream(recv.getInputStream());
                        input.read();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
