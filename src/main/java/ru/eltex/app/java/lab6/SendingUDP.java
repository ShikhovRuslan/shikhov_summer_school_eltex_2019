package ru.eltex.app.java.lab6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static ru.eltex.app.java.lab6.Server.getPortUdp;

public class SendingUDP implements Runnable {

    private String message;
    private String host;

    SendingUDP(String message, String host) {
        this.message = message;
        this.host = host;
    }

    static void sendMessage(String msg, int port, String hst) {
        byte[] data;
        InetAddress address;
        DatagramPacket pack;
        DatagramSocket ds;

        try {
            data = msg.getBytes();
            address = InetAddress.getByName(hst);
            pack = new DatagramPacket(data, data.length, address, port);
            ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < Server.getNumberPortsUdp(); i++) {
                sendMessage(message, getPortUdp() + i, host);
            }
        }
    }

}