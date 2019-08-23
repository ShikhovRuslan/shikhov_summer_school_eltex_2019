package ru.eltex.app.java.lab2;

import java.io.Serializable;
import java.util.*;

public class Orders implements Serializable {

    private List<Order> orders;
    private Map<Date, Order> dateOrder;

    public Orders() {
        orders = new ArrayList<>();
        dateOrder = new TreeMap<>();
    }

    public void setDateOrder(Map<Date, Order> dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void add(Order order) {
        orders.add(order);
        dateOrder.put(order.getDateCreate(), order);
    }

    public void remove(Order order) {
        Iterator it;
        it = orders.iterator();
        while (it.hasNext()) {
            if (order == it.next()) {
                it.remove();
            }
        }

        it = dateOrder.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() == order) {
                it.remove();
            }
        }
    }

    public void makePurchase(ShoppingCart cart, Credentials user) {
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

    public void showStatusesOfOrders() {
        Iterator it = orders.iterator();
        if (!it.hasNext()) {
            System.out.print("no orders");
        }
        while (it.hasNext()) {
            Order order = (Order) it.next();
            if (order.getStatus() == OrderStatus.WAITING) {
                System.out.print("w");
            } else {
                System.out.print("d");
            }
        }
        System.out.println("");
    }

    public void checkTime() {
        Iterator it = orders.iterator();
        if (!it.hasNext()) {
            System.out.println("no orders for checking time");
        }
        while (it.hasNext()) {
            Order order = (Order) it.next();
            if (order.getStatus() == OrderStatus.WAITING) {
                order.setStatus(OrderStatus.DONE);
                System.out.print("check time: status change     ");
                showStatusesOfOrders();
            } else {
                System.out.println("check time: no status change");
            }
        }
        System.out.println("");
    }

    public void checkDone() {
        Iterator it = orders.iterator();
        if (!it.hasNext()) {
            System.out.println("no orders for checking done");
        }
        while (it.hasNext()) {
            Order order = (Order) it.next();
            if (order.getStatus() == OrderStatus.DONE) {
                it.remove();
                System.out.print("check done: removing     ");
                showStatusesOfOrders();
            } else {
                System.out.println("check done: no removing");
            }
        }
        System.out.println("");
    }

    public void show() {
        for (Order order : orders) {
            order.show();
        }
    }

    public void showShort() {
        for (Order order : orders) {
            order.showShort();
            System.out.print("\r");
        }
        System.out.println("");
    }

}