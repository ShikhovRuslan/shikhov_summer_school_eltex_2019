package ru.eltex.app.java.lab6;

import static ru.eltex.app.java.lab6.Server.getPortUdp;

public class SendingUDP implements Runnable {

    private int port;
    private Server server;

    SendingUDP(int port, Server server) {
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            server.sendMessage(Integer.toString(port), getPortUdp());
        }
    }

}