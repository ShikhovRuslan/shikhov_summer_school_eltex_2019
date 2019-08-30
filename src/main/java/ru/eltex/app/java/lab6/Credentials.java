package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab3.Order;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Credentials extends ru.eltex.app.java.lab2.Credentials implements Runnable {

    private Server server;
    private int number;
    private boolean isActive;

    Credentials(Server server, int number) {
        this.server = server;
        this.number = number;
        isActive = true;
    }

    private long sendOrder(Order order, String hostAddress, int port) throws Exception {
        Socket socket;
        DataInputStream inStream;
        DataOutputStream outStream;
        ObjectOutputStream oos;
        long timeWrite;

        socket = new Socket(hostAddress, port);
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());
        oos = new ObjectOutputStream(outStream);

        oos.writeObject(order);
        oos.writeObject(number);
        timeWrite = System.currentTimeMillis();
        System.out.println("клиент " + number + " отправил заказ\n");

        inStream.close();
        outStream.close();
        socket.close();

        return timeWrite;
    }

    private DatagramPacket connectToServer(int port) throws Exception {
        DatagramSocket ds;
        DatagramPacket pack;

        ds = new DatagramSocket(port);
        pack = new DatagramPacket(new byte[1024], 1024);
        ds.receive(pack);
        ds.close();

        return pack;
    }

    private boolean analyseAnswer(String str, Order order, long time) {
        synchronized (System.out) {
            if (str.equals(server.getAnswer())) {
                order.setStatus(OrderStatus.DONE);
                order.show();
                System.out.println("время обработки заказа: " + time + "\n");
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void run() {
        DatagramPacket pack, pack1;
        InetAddress address;
        int port;
        Order order;
        long timeWrite, timeReceive, time;
        String answer;
        boolean isStatusChanged;

        try {
            System.out.println("клиент " + number + " запущен\n");

            pack = connectToServer(server.getPortUdp() + number);
            System.out.println("клиент " + number + " принял пакет\n");
            address = pack.getAddress();
            port = new Integer(new String(pack.getData()).substring(0, pack.getLength()));

            do {
                order = Order.generate((int) (Math.random() * 3) + 1);
                timeWrite = sendOrder(order, address.getHostAddress(), port);

                pack1 = connectToServer(server.getPortUdpReply() + number);
                timeReceive = System.currentTimeMillis();
                System.out.println("клиент " + number + " получил ответ об изменении статуса заказа\n");
                answer = (new String(pack1.getData())).substring(0, server.getAnswer().length());
                time = timeReceive - timeWrite - server.getPauseNotification();

                isStatusChanged = analyseAnswer(answer, order, time);
            } while (isStatusChanged && isActive);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (!isActive) {
            synchronized (System.out) {
                System.out.println("клиент " + number + " завершил работу\n");
            }
        }
    }

    void stop() {
        isActive = false;
    }

}