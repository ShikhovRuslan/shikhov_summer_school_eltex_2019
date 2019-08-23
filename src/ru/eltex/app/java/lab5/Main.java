package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.Order;
import ru.eltex.app.java.lab2.Orders;
import ru.eltex.app.java.lab2.ShoppingCart;

import java.util.UUID;

public class Main {

    static final String FILE_ORDERS = "/home/ruslan/summer_school_2019/labs_java/orders.ser";
    static final String JSON_ORDERS = "/home/ruslan/summer_school_2019/labs_java/orders.json";

    public static void main(String[] args) {
        Orders orders = new Orders();
        Orders ordersReadMof, ordersReadMoj;
        Order order2ReadMof, order2ReadMoj, orderRandomReadMof, orderRandomReadMoj;
        UUID idRandom = UUID.randomUUID();
        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = new ShoppingCart();
        ShoppingCart cart3 = new ShoppingCart();
        ShoppingCart cart4 = new ShoppingCart();
        Credentials user1 = new Credentials();
        Credentials user2 = new Credentials();
        Credentials user3 = new Credentials();
        Credentials user4 = new Credentials();
        user1.create();
        user2.create();
        user3.create();
        user4.create();


        Phone phone1 = new Phone();
        phone1.create();
        cart1.add(phone1);

        Tablet tablet1 = new Tablet();
        tablet1.create();
        cart1.add(tablet1);

        Smartphone smartphone2 = new Smartphone();
        smartphone2.create();
        cart2.add(smartphone2);

        Tablet tablet3 = new Tablet();
        tablet3.create();
        cart3.add(tablet3);

        Smartphone smartphone4 = new Smartphone();
        smartphone4.create();
        cart4.add(smartphone4);


        Order order1 = new Order(cart1, user1);
        Order order2 = new Order(cart2, user2);
        orders.add(order1);
        orders.add(order2);

        Order orderInstead1 = new Order(cart3, user3);
        orderInstead1.setId(order1.getId());

        Order orderWithNewId = new Order(cart4, user4);


        orders.show();
        System.out.println("\n\n");

        orderInstead1.show();
        System.out.println("\n\n");
        orderWithNewId.show();

        System.out.println("\n\n--------------------ДВОИЧНЫЙ ФАЙЛ--------------------\n\n\n");
        ManagerOrderFile mof = new ManagerOrderFile(FILE_ORDERS);
        mof.saveAll(orders);
        order2ReadMof = mof.readById(order2.getId());
        order2ReadMof.show();
        orderRandomReadMof = mof.readById(idRandom);
        System.out.println("\n\n");
        mof.saveById(orderInstead1);
        mof.saveById(orderWithNewId);
        ordersReadMof = mof.readAll();
        ordersReadMof.show();

        System.out.println("\n\n---------------------ФОРМАТ JSON---------------------\n\n\n");
        ManagerOrderJSON moj = new ManagerOrderJSON(JSON_ORDERS);
        moj.saveAll(orders);
        order2ReadMoj = moj.readById(order2.getId());
        order2ReadMoj.show();
        orderRandomReadMoj = moj.readById(idRandom);
        System.out.println("\n\n");
        moj.saveById(orderInstead1);
        moj.saveById(orderWithNewId);
        ordersReadMoj = moj.readAll();
        ordersReadMoj.show();
    }

}