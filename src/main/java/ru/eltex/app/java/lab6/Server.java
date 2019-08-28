package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.sql.Date;
import java.util.*;

public class Server {

    private final int PORT_UDP = 3333;
    private final int PORT_UDP_REPLY = 1845;
    private final int PORT = 4405;
    private final String LOCAL_HOST = "localhost";
    private final int NUMBER_PORTS_UDP = 1000;
    private final int WAITING_FOR_CREDENTIALS = 10000;
    private final long PAUSE_SERVER_CONNECTION = 1500;
    private final String ANSWER = "status changed";
    private Orders<LinkedList<Order>, TreeMap<Date, Order>> orders;
    private TreeMap<UUID, Integer> usersIds;

    Server() {
        orders = new Orders<>(new LinkedList<>(), new TreeMap<>());
        usersIds = new TreeMap<>();
    }

    int getPortUdp() {
        return PORT_UDP;
    }

    long getPauseServerConnection() {
        return PAUSE_SERVER_CONNECTION;
    }

    int getPort() {
        return PORT;
    }

    String getLocalHost() {
        return LOCAL_HOST;
    }

    String getAnswer() {
        return ANSWER;
    }

    int getWaitingForCredentials() {
        return WAITING_FOR_CREDENTIALS;
    }

    int getPortUdpReply() {
        return PORT_UDP_REPLY;
    }

    int getNumberPortsUdp() {
        return NUMBER_PORTS_UDP;
    }

    Orders<LinkedList<Order>, TreeMap<Date, Order>> getOrders() {
        return orders;
    }

    TreeMap<UUID, Integer> getUsersIds() {
        return usersIds;
    }

}