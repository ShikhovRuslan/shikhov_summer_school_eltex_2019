package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab3.Order;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnection implements Runnable {

    private Server server;
    private Socket socket;
    private int numberUser;

    ServerConnection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
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
        numberUser = (int) ois.readObject();
        System.out.println("заказ от клиента " + numberUser + " получен\n");

        inStream.close();
        outStream.close();
        socket.close();

        return order;
    }

    @Override
    public void run() {
        Order order = null;
        try {
            order = receiveOrder();
        } catch (Exception e) {
            System.out.println(e);
        }
        server.saveOrder(order, numberUser);
    }

}