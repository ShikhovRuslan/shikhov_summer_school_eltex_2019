package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Orders<T extends List<Order>, U extends Map<Date, Order>> implements Serializable {

    private T orders;
    private U dateOrder;

    public Orders() {
        List<Order> orders = new ArrayList<>();
        Map<Date, Order> dateOrder = new TreeMap<>();
        this.orders = (T) orders;
        this.dateOrder = (U) dateOrder;
    }

    public Orders(T orders, U dateOrder) {
        this.orders = orders;
        this.dateOrder = dateOrder;
        for (Order order : orders) {
            dateOrder.put(order.getDateCreate(), order);
        }
    }

    public U getDateOrder() {
        return dateOrder;
    }

    public T getOrders() {
        return orders;
    }

    public void add(Order order) {
        orders.add(order);
        dateOrder.put(order.getDateCreate(), order);
    }

    <T extends List<Device>, U extends Set<UUID>> void makePurchase(ShoppingCart<T, U> cart, Credentials user) {
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

    public void checkTime() {
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.WAITING) {
                order.setStatus(OrderStatus.DONE);
            }
        }
    }

    public void show() {
        if (orders.size() == 0) {
            System.out.println("Заказов нет!");
        }
        for (Order order : orders) {
            order.show();
        }
    }

}