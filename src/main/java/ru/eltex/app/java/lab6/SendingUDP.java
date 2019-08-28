package ru.eltex.app.java.lab6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static ru.eltex.app.java.lab6.Server.getPortUdp;

public class SendingUDP implements Runnable {

    private String message;
    private String host;
    private boolean isActive;

    SendingUDP(String message, String host) {
        this.message = message;
        this.host = host;
        isActive = true;
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
        while (isActive) {
            for (int i = 0; i < Server.getNumberPortsUdp(); i++) {
                sendMessage(message, getPortUdp() + i, host);
            }
        }
    }

    void stop(){
        isActive = false;
    }

}