package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab2.Orders;
import ru.eltex.app.java.lab3.ShoppingCart;
import ru.eltex.app.java.lab5.ManagerOrderFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        System.out.println("receiver is running");
        try {
            DatagramSocket ds = new DatagramSocket(Server.getPort());
            while (true) {
                DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
                ds.receive(pack);
                InetAddress address = pack.getAddress();

                System.out.println(new String(pack.getData()) + "\n" + address.getHostAddress());

                int port = new Integer(new String(pack.getData()).substring(0,pack.getLength()));

                System.out.println("client is running\n");

                Socket socket = new Socket("localhost", port);

                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inStream = new DataInputStream(socket.getInputStream());

                ShoppingCart cart = new ShoppingCart();
                Credentials user = new Credentials();
                user.create();
                Phone phone = new Phone();
                phone.create();
                cart.add(phone);
                Order order = new Order(cart, user);

                ObjectOutputStream oos = new ObjectOutputStream(outStream);
                oos.writeObject(order);

                inStream.close();
                outStream.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}