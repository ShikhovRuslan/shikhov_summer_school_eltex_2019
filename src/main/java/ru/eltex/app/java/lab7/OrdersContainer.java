package ru.eltex.app.java.lab7;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab3.ShoppingCart;

import java.sql.Date;
import java.util.*;

public class OrdersContainer {

    private Orders<LinkedList<Order>, HashMap<Date, Order>> orders = new Orders<>();

    OrdersContainer() {
        ShoppingCart<ArrayList<Device>, TreeSet<UUID>> cart1, cart2;
        Credentials user1, user2;
        Device phone1, tablet1, tablet2, smartphone1, smartphone2;
        Order order1, order2;

        cart1 = new ShoppingCart<>();
        cart2 = new ShoppingCart<>();

        user1 = new Credentials();
        user2 = new Credentials();

        user1.create();
        user2.create();

        phone1 = new Phone();
        tablet1 = new Tablet();
        tablet2 = new Tablet();
        smartphone1 = new Smartphone();
        smartphone2 = new Smartphone();

        phone1.create();
        tablet1.create();
        tablet2.create();
        smartphone1.create();
        smartphone2.create();

        cart1.add(phone1);
        cart1.add(tablet1);
        cart1.add(tablet2);
        cart2.add(smartphone1);
        cart2.add(smartphone2);

        order1 = new Order(cart1, user1);
        order2 = new Order(cart2, user2);
        orders.add(order1);
        orders.add(order2);
    }

    Orders<LinkedList<Order>, HashMap<Date, Order>> getOrders() {
        return orders;
    }

}