package ru.eltex.app.java.lab6;

import static ru.eltex.app.java.lab6.Server.getPortUdp;

public class SendingUDP implements Runnable {

    private int port;
    private String host;

    SendingUDP(int port, String host) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        while (true) {
            Server.sendMessage(Integer.toString(port), getPortUdp(), host);
        }
    }

}