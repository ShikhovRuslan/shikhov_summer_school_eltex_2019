package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;

import java.util.*;

public class OrdersGeneric<T extends List<Order>, U extends Map<Date, Order>> {

    private T orders;
    private U dateOrder;

    OrdersGeneric(T orders, U dateOrder) {
        this.orders = orders;
        this.dateOrder = dateOrder;
        for (Order order : orders) {
            dateOrder.put(order.getDateCreate(), order);
        }
    }

    List<Order> getOrders() {
        return orders;
    }

    void add(Order order) {
        orders.add(order);
        dateOrder.put(order.getDateCreate(), order);
    }

    <T extends List<Device>, U extends Set<UUID>> void makePurchase(ShoppingCartGeneric<T, U> cart, Credentials user) {
        add(new Order(cart, user));
    }

    void check() {
        Date date = null;
        Iterator it1 = orders.iterator();
        while (it1.hasNext()) {
            Order order = (Order) it1.next();
            if (order.checkInterval(System.currentTimeMillis()) && order.getStatus() == OrderStatus.DONE) {
                it1.remove();
                date = order.getDateCreate();
                dateOrder.remove(date);
            }
        }
        Iterator it2 = orders.iterator();
        while (it2.hasNext()) {
            Order order = (Order) it2.next();
            if (order.getDateCreate() == date) {
                dateOrder.put(date, order);
            }
        }
    }

    void show() {
        if (orders.size() == 0) {
            System.out.println("Заказов нет!");
        }
        for (Order order : orders) {
            order.show();
        }
    }

}