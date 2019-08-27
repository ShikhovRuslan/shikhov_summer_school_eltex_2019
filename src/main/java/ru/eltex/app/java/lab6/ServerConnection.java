package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.LinkedList;
import java.util.TreeMap;

public class ServerConnection implements Runnable {

    private static final long PAUSE = 1500;
    private static final String ANSWER = "status changed";
    private Socket socket;
    private Server server;
    private int countClient;

    ServerConnection(Socket socket, Server server, int countClient) {
        this.socket = socket;
        this.server = server;
        this.countClient = countClient;
    }

    static long getPause(){
        return PAUSE;
    }

    static String getAnswer(){
        return ANSWER;
    }

    private Order receiveOrder() throws Exception {
        DataInputStream inStream;
        DataOutputStream outStream;
        ObjectInputStream ois;
        Order order;

        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(inStream);

        order = (Order) ois.readObject();
        System.out.println("reading info from client " + countClient);

        inStream.close();
        outStream.close();
        socket.close();

        return order;
    }

    @Override
    public void run() {
        Order order;
        Orders<LinkedList<Order>, TreeMap<Date, Order>> orders;

        try {
            order = receiveOrder();

            orders = new Orders<>();
            orders.add(order);
            orders.checkTime();

            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            server.sendMessage(getAnswer(), server.getPortUdpReply());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}