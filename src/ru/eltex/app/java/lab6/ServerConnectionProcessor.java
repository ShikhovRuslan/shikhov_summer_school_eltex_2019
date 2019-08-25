package ru.eltex.app.java.lab6;

import ru.eltex.app.java.lab2.Order;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnectionProcessor extends Thread {

    private Socket socket;

    ServerConnectionProcessor(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream ois = new ObjectInputStream(inStream);
            Order order = (Order) ois.readObject();
            order.show();

            //outStream.writeInt(result);
            // Подождем немного и завершим поток
            sleep(1000);

            inStream.close();
            outStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}