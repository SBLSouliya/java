package Shape;

import Shape.ShapeThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ShapeServer {
    public final static int SERVER_PORT = 999;////172.20.10.9
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    ShapeThread thread = new ShapeThread(socket);
                    thread.start();
                } catch (IOException e) {System.err.println(" Connection Error: " + e);}
            }
        } catch (IOException e1) {e1.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }
}