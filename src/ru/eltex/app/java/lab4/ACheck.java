package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Orders;

public abstract class ACheck implements Runnable {

    protected boolean isActive;
    protected long pause;
    protected Orders orders;

    ACheck(Orders orders, long pause) {
        this.orders = orders;
        this.pause = pause;
        isActive = true;
    }

    public void stop() {
        isActive = false;
    }

}