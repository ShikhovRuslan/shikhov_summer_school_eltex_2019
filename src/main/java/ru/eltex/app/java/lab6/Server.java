package ru.eltex.app.java.lab6;

import java.net.*;

public class Server {

    private static final int PORT_UDP = 3333;
    private static final int PORT_UDP_REPLY = 1845;
    private static final int PORT = 4405;
    private static final String LOCAL_HOST = "localhost";

    static int getPortUdp() {
        return PORT_UDP;
    }

    static int getPortUdpReply() {
        return PORT_UDP_REPLY;
    }

    static void sendMessage(String message, int port, String host) {
        byte[] data;
        InetAddress address;
        DatagramPacket pack;
        DatagramSocket ds;

        try {
            data = message.getBytes();
            address = InetAddress.getByName(host);
            pack = new DatagramPacket(data, data.length, address, port);
            ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Server server;
        ServerSocket serverSocket;
        SendingUDP send;
        Thread threadSend, threadServerConnection;
        int i = 0;
        Socket socket;
        ServerConnection serverConnection;

        server = new Server();
        try {
            System.out.println("server is running");
            serverSocket = new ServerSocket(PORT);

            send = new SendingUDP(PORT, LOCAL_HOST);
            threadSend = new Thread(send);
            threadSend.start();

            while (true) {
                i++;
                System.out.println("waiting for clients (" + i + ")");
                socket = serverSocket.accept();
                System.out.println("client " + i + " is accepted");

                serverConnection = new ServerConnection(socket, server, LOCAL_HOST, i);
                threadServerConnection = new Thread(serverConnection);
                threadServerConnection.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}