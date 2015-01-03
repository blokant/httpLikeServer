package com.trenkinan.experiments.httplikeserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static final int DEFAULT_PORT = 8080;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final String serverRoot;
    private final int port;
    public Server(String serverRoot, int port) {
        this.port = port;
        this.serverRoot = serverRoot;
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.out.println("Port is busy: " + port);
            System.exit(-1);
        }
        System.out.println("com.trenkinan.experiments.httplikeserver.Server root: " + this.serverRoot);
    }
    void start() {
        while (true) {
            clientSocket = null;
            try {
                System.out.println("com.trenkinan.experiments.httplikeserver.Server is waiting for new connection...");
                clientSocket = serverSocket.accept();
                System.out.println("\n\n\nserver: " + "accept invoked");
                new Thread(new HttpHelper(clientSocket, this.serverRoot)).start();
            } catch (IOException e) {
                System.out.println("Error while getting this port: " + DEFAULT_PORT);
                System.exit(-1);
            }
        }

    }
}