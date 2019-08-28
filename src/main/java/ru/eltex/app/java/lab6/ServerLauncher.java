package ru.eltex.app.java.lab6;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerLauncher {

    public static void main(String[] args) {
        Server server;
        ServerSocket serverSocket;
        SendingUDP send = null;
        Thread threadSend, threadServerConnection, threadScn;
        Socket socket;
        ServerConnection serverConnection;
        StatusChangeNotification scn = null;

        server = new Server();
        System.out.println("сервер запущен\n");

        try {
            serverSocket = new ServerSocket(server.getPort());

            // рассылка порта PORT потенциальным клиентам, используя порт PORT_UDP
            send = new SendingUDP(server, Integer.toString(server.getPort()), server.getLocalHost());
            threadSend = new Thread(send);
            threadSend.start();

            // рассылка уведомлений о смене статусов заказов
            scn = new StatusChangeNotification(server, server.getLocalHost());
            threadScn = new Thread(scn);
            threadScn.start();

            while (true) {
                System.out.println("ожидание клиентов\n");
                serverSocket.setSoTimeout(server.getWaitingForCredentials());
                socket = serverSocket.accept();
                System.out.println("клиент принят\n");

                // работа с подключившимся клиентом в новом потоке
                // после обработки полученного от клиента заказа отправка ему сообщения ANSWER, используя порт PORT_UDP_REPLY
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