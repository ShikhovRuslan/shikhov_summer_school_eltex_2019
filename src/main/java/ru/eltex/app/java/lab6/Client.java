package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab3.Order;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import static ru.eltex.app.java.lab3.Order.generateOrder;

public class Client {

    private int count = 0;

    private Client() {
        count++;
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
        timeWrite = System.currentTimeMillis();
        System.out.println("client " + count + " sent order to server\n");

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

    private boolean analizeAnswer(String str, Order order, long time) {
        if (str.equals(ServerConnection.getAnswer())) {
            order.show();
            System.out.println("время обработки заказа: " + time);
            return true;
        } else {
            return false;
        }
    }

    private void run() {
        DatagramPacket pack, pack1;
        InetAddress address;
        int port;
        Order order;
        long timeWrite, timeReceive, time;
        String answer;
        boolean isStatusChanged;

        try {
            System.out.println("receiver " + count + " is running");

            pack = connectToServer(Server.getPortUdp());
            System.out.println("pack received by " + count);
            address = pack.getAddress();
            port = new Integer(new String(pack.getData()).substring(0, pack.getLength()));

            do {
                order = generateOrder((int) (Math.random() * 3) + 1);
                timeWrite = sendOrder(order, address.getHostAddress(), port);

                pack1 = connectToServer(Server.getPortUdpReply());
                timeReceive = System.currentTimeMillis();
                System.out.println("client " + count + " received answer from server");
                answer = (new String(pack1.getData())).substring(0, ServerConnection.getAnswer().length());
                time = timeReceive - timeWrite - ServerConnection.getPause();

                isStatusChanged = analizeAnswer(answer, order, time);
            } while (isStatusChanged);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();

        client1.run();
        client2.run();
    }

}