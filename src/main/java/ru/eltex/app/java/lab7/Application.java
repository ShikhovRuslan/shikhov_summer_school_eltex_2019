package ru.eltex.app.java.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab3.ShoppingCart;
import ru.eltex.app.java.lab5.Main;
import ru.eltex.app.java.lab5.ManagerOrderJSON;

import java.sql.Date;
import java.util.*;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static Orders<LinkedList<Order>, HashMap<Date, Order>> orders = new Orders<>();

    public static Orders<LinkedList<Order>, HashMap<Date, Order>> getOrders() {
        return orders;
    }

    public static void main(String[] args) {
        ShoppingCart<ArrayList<Device>, TreeSet<UUID>> cart1, cart2;
        Credentials user1, user2;
        Device phone, tablet;
        Order order1, order2;
        ManagerOrderJSON moj;

        cart1 = new ShoppingCart<>();
        cart2 = new ShoppingCart<>();

        user1 = new Credentials();
        user2 = new Credentials();

        user1.create();
        user2.create();

        phone = new Phone();
        phone.create();
        cart1.add(phone);

        tablet = new Tablet();
        tablet.create();
        cart2.add(tablet);

        order1 = new Order(cart1, user1);
        order2 = new Order(cart2, user2);
        order2.setId(UUID.fromString("00000000-0000-0000-0000-000000000021"));
        orders.add(order1);
        orders.add(order2);

        moj = new ManagerOrderJSON(Main.getJsonOrders());
        moj.saveAll(orders);

        SpringApplication.run(Application.class, args);
    }

}