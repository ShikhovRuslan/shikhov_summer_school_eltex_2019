package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab3.Order;

import java.util.ArrayList;

public class StatusChangeNotification implements Runnable {

    private final long PAUSE = 3000;
    private Server server;
    private String host;
    private boolean isActive;

    StatusChangeNotification(Server server, String host) {
        this.server = server;
        this.host = host;
        isActive = true;
    }

    @Override
    public void run() {
        while (isActive) {
            ArrayList<Integer> mas = new ArrayList<>();
            for (Order order : server.getOrders().getOrders()) {
                if (order.getStatus() == OrderStatus.WAITING) {
                    order.setStatus(OrderStatus.DONE);
                    mas.add(server.getUsersIds().get(order.getUser().getId()));
                }
            }
            for (Integer i : mas) {
                SendingUDP.sendMessage(server.getAnswer(), server.getPortUdpReply() + i, host);
            }
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void stop() {
        isActive = false;
    }

}