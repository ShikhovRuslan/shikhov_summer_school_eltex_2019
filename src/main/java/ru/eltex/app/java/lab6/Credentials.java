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
import java.util.concurrent.locks.ReentrantLock;

import static ru.eltex.app.java.lab3.Order.generateOrder;

public class Credentials extends ru.eltex.app.java.lab2.Credentials implements Runnable {

    private int number;

    private Credentials(int number) {
        this.number = number;
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
            if (str.equals(ServerConnection.getAnswer())) {
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

            pack = connectToServer(Server.getPortUdp() + number);
            System.out.println("клиент " + number + " принял пакет\n");
            address = pack.getAddress();
            port = new Integer(new String(pack.getData()).substring(0, pack.getLength()));

            do {
                order = generateOrder((int) (Math.random() * 3) + 1);
                timeWrite = sendOrder(order, address.getHostAddress(), port);

                pack1 = connectToServer(Server.getPortUdpReply() + number);
                timeReceive = System.currentTimeMillis();
                System.out.println("клиент " + number + " получил ответ об изменении статуса заказа\n");
                answer = (new String(pack1.getData())).substring(0, ServerConnection.getAnswer().length());
                time = timeReceive - timeWrite - ServerConnection.getPause();
                order.setStatus(OrderStatus.DONE);

                isStatusChanged = analyseAnswer(answer, order, time);
            } while (isStatusChanged);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Credentials user1, user2;
        Thread threadUser1, threadUser2;

        user1 = new Credentials(1);
        user2 = new Credentials(2);

        threadUser1 = new Thread(user1);
        threadUser2 = new Thread(user2);

        threadUser1.start();
        threadUser2.start();
    }

}