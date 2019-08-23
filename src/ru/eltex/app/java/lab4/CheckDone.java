package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Orders;

public class CheckDone extends ACheck {

    CheckDone(Orders orders, long pause) {
        super(orders, pause);
    }

    @Override
    public void run() {
        while (isActive) {
            synchronized (orders) {
                orders.checkDone();
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}