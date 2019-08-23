package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.Order;
import ru.eltex.app.java.lab2.Orders;
import ru.eltex.app.java.lab2.ShoppingCart;

public class Main {

    static final String FILE_ORDERS = "/home/ruslan/summer_school_2019/labs_java/orders.ser";
    static final String JSON_ORDERS = "/home/ruslan/summer_school_2019/labs_java/orders.json";

    public static void main(String[] args) {
        Orders orders = new Orders();
        Orders ordersRead1;
        Orders ordersRead2;
        Order order0, order1, order2;
        ShoppingCart cart0 = new ShoppingCart();
        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = new ShoppingCart();
        Credentials user0 = new Credentials();
        Credentials user1 = new Credentials();
        Credentials user2 = new Credentials();
        user0.create();
        user1.create();
        user2.create();


        Tablet tablet0 = new Tablet();
        tablet0.create();
        user0.create();
        cart0.add(tablet0);

        order0 = new Order(cart0, user0);

        Phone phone1 = new Phone();
        phone1.create();
        cart1.add(phone1);

        Tablet tablet1 = new Tablet();
        tablet1.create();
        cart1.add(tablet1);

        Smartphone smartphone2 = new Smartphone();
        smartphone2.create();
        cart2.add(smartphone2);

        order1 = new Order(cart1, user1);
        order2 = new Order(cart2, user2);
        orders.add(order1);
        orders.add(order2);


        orders.show();

        System.out.println("\n\n--------------------ДВОИЧНЫЙ ФАЙЛ--------------------\n\n\n");
        ManagerOrderFile mof = new ManagerOrderFile(FILE_ORDERS);
        mof.saveAll(orders);
        ordersRead1 = mof.readAll();
        ordersRead1.show();
        order2Read = readById(order2.getId());

        System.out.println("\n\n---------------------ФОРМАТ JSON---------------------\n\n\n");
        ManagerOrderJSON moj = new ManagerOrderJSON(JSON_ORDERS);
        moj.saveAll(orders);
        ordersRead2 = moj.readAll();
        ordersRead2.show();
    }

}