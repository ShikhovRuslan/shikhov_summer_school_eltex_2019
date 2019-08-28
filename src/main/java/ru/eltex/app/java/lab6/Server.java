package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.net.*;
import java.sql.Date;
import java.util.*;

public class Server {

    private static final int PORT_UDP = 3333;
    private static final int PORT_UDP_REPLY = 1845;
    private static final int PORT = 4405;
    private static final String LOCAL_HOST = "localhost";
    private static final int NUMBER_PORTS_UDP = 1000;
    private static Orders<LinkedList<Order>, TreeMap<Date, Order>> orders;
    private static TreeMap<UUID, Integer> usersIds;
    private static long SERVER_TIME_WORKING = 10000;
    private static int WAITING_FOR_CREDENTIALS = 10000;

    private Server() {
        Server.orders = new Orders<>(new LinkedList<>(), new TreeMap<>());
        Server.usersIds = new TreeMap<>();
    }

    static int getPortUdp() {
        return PORT_UDP;
    }

    static int getPortUdpReply() {
        return PORT_UDP_REPLY;
    }

    static int getNumberPortsUdp() {
        return NUMBER_PORTS_UDP;
    }

    static Orders<LinkedList<Order>, TreeMap<Date, Order>> getOrders() {
        return orders;
    }

    static TreeMap<UUID, Integer> getUsersIds() {
        return usersIds;
    }

    static long getServerTimeWorking() {
        return SERVER_TIME_WORKING;
    }

    public static void main(String[] args) {
        Server server;
        ServerSocket serverSocket;
        SendingUDP send = null;
        Thread threadSend, threadServerConnection, threadScn;
        Socket socket;
        ServerConnection serverConnection;
        StatusChangeNotification scn = null;

        server = new Server();
        System.out.println("сервер запущен\n");

        try {
            serverSocket = new ServerSocket(PORT);

            // рассылка порта PORT потенциальным клиентам, используя порт PORT_UDP
            send = new SendingUDP(Integer.toString(PORT), LOCAL_HOST);
            threadSend = new Thread(send);
            threadSend.start();

            // рассылка уведомлений о смене статусов заказов
            scn = new StatusChangeNotification(LOCAL_HOST);
            threadScn = new Thread(scn);
            threadScn.start();

            while (true) {
                System.out.println("ожидание клиентов\n");
                serverSocket.setSoTimeout(WAITING_FOR_CREDENTIALS);
                socket = serverSocket.accept();
                System.out.println("клиент принят\n");

                // работа с подключившимся клиентом в новом потоке
                // после обработки полученного от клиента заказа отправка ему сообщения ANSWER, используя порт PORT_UDP_REPLY
                serverConnection = new ServerConnection(socket);
                threadServerConnection = new Thread(serverConnection);
                threadServerConnection.start();
            }
        } catch (SocketTimeoutException ste) {
            System.out.println("ожидание подключения клиентов завершено");
        } catch (Exception e) {
            System.out.println(e);
        }

        send.stop();
        scn.stop();
    }

}