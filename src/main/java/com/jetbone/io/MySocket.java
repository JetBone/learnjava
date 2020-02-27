package com.jetbone.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chris on 2020/1/2
 */
public class MySocket {

    public static void main(String[] args) throws Exception {

        Thread client  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 8008);


                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
