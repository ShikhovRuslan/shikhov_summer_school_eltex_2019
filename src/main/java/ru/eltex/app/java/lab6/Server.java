package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.sql.Date;
import java.util.*;

public class Server {

    private final int PORT = 4405;
    private final int PORT_UDP = 3333;
    private final int PORT_UDP_REPLY = 1845;
    private final String LOCAL_HOST = "localhost";
    private final int NUMBER_PORTS_UDP = 1000;
    private final int WAITING_FOR_CREDENTIALS = 10000;
    private final long PAUSE_NOTIFICATION = 700;
    private final String ANSWER = "status changed";
    private boolean areStatusesChanged = false;
    private Orders<LinkedList<Order>, TreeMap<Date, Order>> orders;
    private TreeMap<UUID, Integer> credentialsIds;

    Server() {
        orders = new Orders<>(new LinkedList<>(), new TreeMap<>());
        credentialsIds = new TreeMap<>();
    }

    int getPort() {
        return PORT;
    }

    int getPortUdp() {
        return PORT_UDP;
    }

    int getPortUdpReply() {
        return PORT_UDP_REPLY;
    }

    String getLocalHost() {
        return LOCAL_HOST;
    }

    int getNumberPortsUdp() {
        return NUMBER_PORTS_UDP;
    }

    int getWaitingForCredentials() {
        return WAITING_FOR_CREDENTIALS;
    }

    long getPauseNotification() {
        return PAUSE_NOTIFICATION;
    }

    String getAnswer() {
        return ANSWER;
    }

    synchronized void saveOrder(Order order, int numberUser) {
        orders.add(order);
        credentialsIds.put(order.getUser().getId(), numberUser);
        System.out.println("===================== сохранение заказа от клиента " + numberUser + " =====================\n");
        areStatusesChanged = false;
        notify();
    }

    synchronized void changeStatuses() {
        while (!areStatusesChanged) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> mas = new ArrayList<>();
            for (Order order : orders.getOrders()) {
                if (order.getStatus() == OrderStatus.WAITING) {
                    order.setStatus(OrderStatus.DONE);
                    mas.add(credentialsIds.get(order.getUser().getId()));
                }
            }
            areStatusesChanged = true;
            try {
                Thread.sleep(getPauseNotification());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Integer i : mas) {
                SendingUDP.sendMessage(ANSWER, PORT_UDP_REPLY + i, LOCAL_HOST);
                System.out.println("--------------- оповещение клиента " + i + " ---------------\n");
            }
        }
    }

}