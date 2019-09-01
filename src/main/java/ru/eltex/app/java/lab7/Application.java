package ru.eltex.app.java.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab5.Main;
import ru.eltex.app.java.lab5.ManagerOrderJSON;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        Order order1, order2;
        Orders<LinkedList<Order>, HashMap<Date, Order>> orders = new Orders<>(new LinkedList<>(), new HashMap<>());
        ManagerOrderJSON moj;

        order1 = Order.generate((int) (Math.random() * 3) + 1);
        order2 = Order.generate((int) (Math.random() * 3) + 1);
        order2.setId(UUID.fromString("00000000-0000-0000-0000-000000000021"));
        orders.add(order1);
        orders.add(order2);

        moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        moj.saveAll(orders);

        SpringApplication.run(Application.class, args);
    }

}