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

    private static int count = 0;
    private static final String ANSWER = "status changed";

    Client() {
        count++;
        System.out.println("receiver " + count + " is running");
        try {
            DatagramSocket ds = new DatagramSocket(Server.getPortUdp());
            DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
            ds.receive(pack);
            ds.close();
            System.out.println("pack received by " + count);

            InetAddress address = pack.getAddress();
            int port = new Integer(new String(pack.getData()).substring(0, pack.getLength()));

            boolean isStatusChanged;
            do {
                long timeWrite, timeReceive;
                Socket socket = new Socket(address.getHostAddress(), port);

                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(outStream);

                Order order = generateOrder((int) (Math.random() * 3) + 1);
                oos.writeObject(order);
                timeWrite = System.currentTimeMillis();

                inStream.close();
                outStream.close();
                socket.close();

                System.out.println("client " + count + " sent info to server\n");

                DatagramSocket ds1 = new DatagramSocket(Server.getPortUdpReply());
                DatagramPacket pack1 = new DatagramPacket(new byte[1024], 1024);
                ds1.receive(pack1);
                timeReceive = System.currentTimeMillis();
                System.out.println("client " + count + " got answer from server:");
                String answer = new String(pack1.getData());
                ds1.close();
                if (answer.substring(0, ANSWER.length()).equals(ANSWER)) {
                    order.show();
                    System.out.println("время обработки заказа: " + (timeReceive - timeWrite));
                    isStatusChanged = true;
                } else {
                    isStatusChanged = false;
                }
            } while (isStatusChanged);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String getAnswer() {
        return ANSWER;
    }

    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();
    }

}