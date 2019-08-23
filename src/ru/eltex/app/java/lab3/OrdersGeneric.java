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
        Iterator it = orders.iterator();
        while (it.hasNext()) {
            Order order = (Order) it.next();
            if (order.checkInterval(System.currentTimeMillis()) && order.getStatus() == OrderStatus.DONE) {
                it.remove();
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