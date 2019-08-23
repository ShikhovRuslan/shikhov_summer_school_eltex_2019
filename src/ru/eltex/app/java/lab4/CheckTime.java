package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Orders;

public class CheckTime extends ACheck {

    CheckTime(Orders orders, long pause) {
        super(orders, pause);
    }

    @Override
    public void run() {
        while (isActive) {
            synchronized (orders) {
                orders.checkTime();
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}