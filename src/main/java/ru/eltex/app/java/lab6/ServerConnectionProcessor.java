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

public class ServerConnectionProcessor implements Runnable {

    private Socket socket;
    private Server server;
    private int countClient;

    ServerConnectionProcessor(Socket socket, Server server, int port, int countClient) {
        this.socket = socket;
        this.server = server;
        this.countClient = countClient;
    }

    @Override
    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(inStream);

            Order order = (Order) ois.readObject();
            System.out.println("reading info from client " + countClient);

            inStream.close();
            outStream.close();
            socket.close();

            Orders<LinkedList<Order>, TreeMap<Date, Order>> orders = new Orders<>();
            orders.add(order);
            orders.checkTime();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            server.sendMessage(Client.getAnswer(), server.getPortUdpReply());


        } catch (Exception e) {
            System.out.println(e);
        }
    }

}