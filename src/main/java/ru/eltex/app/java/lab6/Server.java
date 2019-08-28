package ru.eltex.app.java.lab6;

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
    private final long PAUSE_NOTIFICATION = 3000;
    private final String ANSWER = "status changed";
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

    Orders<LinkedList<Order>, TreeMap<Date, Order>> getOrders() {
        return orders;
    }

    TreeMap<UUID, Integer> getCredentialsIds() {
        return credentialsIds;
    }

}