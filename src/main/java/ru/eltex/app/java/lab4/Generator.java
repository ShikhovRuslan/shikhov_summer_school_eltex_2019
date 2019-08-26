package ru.eltex.app.java.lab4;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.*;

public class Generator extends ACheck {

    int number;

    Generator(Orders orders, long pause, int number) {
        super(orders, pause);
        this.number = number;
    }

    @Override
    public void run() {
        while (isActive) {
            ShoppingCart cart = new ShoppingCart();
            Credentials user = new Credentials();

            Device phone = new Phone();
            Device smartPhone = new Smartphone();
            Device tablet = new Tablet();

            phone.create();
            smartPhone.create();
            tablet.create();

            cart.add(phone);
            cart.add(smartPhone);
            cart.add(tablet);

            user.create();

            synchronized (orders) {
                orders.makePurchase(cart, user);
                System.out.print("создание заказа " + number + "-м генератором, ");
                System.out.println("список статусов заказов (w - waiting, d - done):");
                orders.showStatusesOfOrders();
                System.out.println("");
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}