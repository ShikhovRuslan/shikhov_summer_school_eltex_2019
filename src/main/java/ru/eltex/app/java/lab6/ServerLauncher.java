package ru.eltex.app.java.lab6;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerLauncher {

    public static void main(String[] args) {
        Server server;
        ServerSocket serverSocket;
        Socket socket;
        SendingUDP send = null;
        StatusChangeNotification scn = null;
        ServerConnection serverConnection;
        Thread threadSend, threadScn, threadServerConnection;

        server = new Server();

        try {
            serverSocket = new ServerSocket(server.getPort());
            System.out.println("сервер запущен\n");

            // рассылка порта PORT потенциальным клиентам, используя порты PORT_UDP...PORT_UDP+NUMBER_PORTS_UDP
            send = new SendingUDP(server, Integer.toString(server.getPort()), server.getLocalHost());
            threadSend = new Thread(send);
            threadSend.start();

            // рассылка уведомлений о смене статусов заказов, полученных от клиентов
            scn = new StatusChangeNotification(server, server.getLocalHost());
            threadScn = new Thread(scn);
            threadScn.start();

            while (true) {
                System.out.println("ожидание клиентов\n");
                serverSocket.setSoTimeout(server.getWaitingForCredentials());
                socket = serverSocket.accept();
                System.out.println("клиент принят\n");

                // получение заказа от клиента
                serverConnection = new ServerConnection(server, socket);
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