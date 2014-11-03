

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static final int DEFAULT_PORT = 8080;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    public Server() {
        int port = DEFAULT_PORT;
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Port is busy: " + port);
            System.exit(-1);
        }
    }
    void start() {
        while (true) {
            clientSocket = null;
            try {
                System.out.println("Server is waiting for new connection...");
                clientSocket = serverSocket.accept();
                System.out.println("server: " + "accept invoked");
                new Thread(new HttpHelper(clientSocket)).start();
            } catch (IOException e) {
                System.out.println("Error while getting this port: " + DEFAULT_PORT);
                System.exit(-1);
            }
        }

    }
}