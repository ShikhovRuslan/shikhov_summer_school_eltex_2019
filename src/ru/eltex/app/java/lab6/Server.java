package ru.eltex.app.java.lab6;

import java.net.*;

public class Server {

    public static int getPort() {
        return PORT;
    }

    private static final int PORT = 3333;
    private String host;
    private int port;

    Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void sendMessage(String mes) {
        try {
            byte[] data = mes.getBytes();
            InetAddress addr = InetAddress.getByName(host);
            DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server("localhost", PORT);
        try {
            System.out.println("server is running");
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                server.sendMessage(Integer.toString(PORT));
                Socket socket = serverSocket.accept();
                ServerConnectionProcessor scp = new ServerConnectionProcessor(socket);
                Thread threadScp = new Thread(scp);
                threadScp.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}