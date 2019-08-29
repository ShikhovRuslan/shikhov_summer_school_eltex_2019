package ru.eltex.app.java.lab6;

public class StatusChangeNotification implements Runnable {

    private Server server;
    private boolean isActive;

    StatusChangeNotification(Server server) {
        this.server = server;
        isActive = true;
    }

    @Override
    public void run() {
        while (isActive) {
            server.changeStatuses();
        }
    }

    void stop() {
        isActive = false;
    }

}