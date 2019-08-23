package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab2.Orders;

public class Main {

    static final long PAUSE_GENERATOR = 700; // 700
    static final double COEFF_PAUSE_CHECK_TIME = 1.5; // 0.75
    static final double COEFF_PAUSE_CHECK_DONE = 0.9; // 0.25

    public static void main(String[] args) {
        Orders orders = new Orders();

        Generator gen1 = new Generator(orders, PAUSE_GENERATOR, 1);
        Generator gen2 = new Generator(orders, (long) (PAUSE_GENERATOR * 1.5), 2);
        Thread thrdGen1 = new Thread(gen1);
        Thread thrdGen2 = new Thread(gen2);
        thrdGen1.start();
        thrdGen2.start();

        CheckTime checkTime = new CheckTime(orders, (long) (PAUSE_GENERATOR * COEFF_PAUSE_CHECK_TIME));
        CheckDone checkDone = new CheckDone(orders, (long) (PAUSE_GENERATOR * COEFF_PAUSE_CHECK_DONE));
        Thread thrdCheckTime = new Thread(checkTime);
        Thread thrdCheckDone = new Thread(checkDone);
        thrdCheckTime.start();
        thrdCheckDone.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gen1.stop();
        gen2.stop();
        checkTime.stop();
        checkDone.stop();
    }

}