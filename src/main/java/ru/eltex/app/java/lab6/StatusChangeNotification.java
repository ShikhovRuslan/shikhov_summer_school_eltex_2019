package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab3.Order;

import java.util.ArrayList;

public class StatusChangeNotification implements Runnable {

    private final static long PAUSE = 3000;
    private String host;
    private boolean isActive;

    StatusChangeNotification(String host) {
        this.host = host;
        isActive = true;
    }

    @Override
    public void run() {
        while (isActive) {
            ArrayList<Integer> mas = new ArrayList<>();
            for (Order order : Server.getOrders().getOrders()) {
                if (order.getStatus() == OrderStatus.WAITING) {
                    order.setStatus(OrderStatus.DONE);
                    mas.add(Server.getUsersIds().get(order.getUser().getId()));
                }
            }
            for (Integer i : mas) {
                SendingUDP.sendMessage(ServerConnection.getAnswer(), Server.getPortUdpReply() + i, host);
            }
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void stop(){
        isActive = false;
    }

}