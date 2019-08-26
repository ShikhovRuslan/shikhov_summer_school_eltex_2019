package ru.eltex.app.java.lab6;

import java.net.*;

public class Server {

    private static final int PORT_UDP = 3333;
    private static final int PORT_UDP_REPLY = 1845;
    private static final int PORT = 4405;
    private String host;

    Server(String host) {
        this.host = host;
    }

    public static int getPortUdp() {
        return PORT_UDP;
    }

    public static int getPortUdpReply() {
        return PORT_UDP_REPLY;
    }

    void sendMessage(String message, int port) {
        try {
            byte[] data = message.getBytes();
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
        Server server = new Server("localhost");
        try {
            System.out.println("server is running");
            ServerSocket serverSocket = new ServerSocket(PORT);

            SendingUDP send = new SendingUDP(PORT, server);
            Thread threadSend = new Thread(send);
            threadSend.start();

            int i = 0;
            while (true) {
                i++;
                System.out.println("waiting for clients (" + i + ")");
                Socket socket = serverSocket.accept();
                System.out.println("client " + i + " is accepted");
                ServerConnectionProcessor scp = new ServerConnectionProcessor(socket, server, PORT, i);
                Thread threadScp = new Thread(scp);
                threadScp.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}