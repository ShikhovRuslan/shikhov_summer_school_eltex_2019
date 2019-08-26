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

    public List<Order> getOrders() {
        return orders;
    }

    public Map<Date, Order> getDateOrder() {
        return dateOrder;
    }

    public void add(Order order) {
        orders.add(order);
        dateOrder.put(order.getDateCreate(), order);
    }

    public void remove(Order order) {
        Date date = null;
        Iterator it1 = orders.iterator();
        while (it1.hasNext()) {
            if (order == it1.next()) {
                it1.remove();
                date = order.getDateCreate();
                dateOrder.remove(date);
            }
        }
        Iterator it2 = orders.iterator();
        while (it2.hasNext()) {
            Order order2 = (Order) it2.next();
            if (order2.getDateCreate() == date) {
                dateOrder.put(date, order2);
            }
        }
    }

    public void makePurchase(ShoppingCart cart, Credentials user) {
        add(new Order(cart, user));
    }

    void check() {
        ArrayList<Date> arrDate = new ArrayList<>();
        Date date;
        Iterator it1 = orders.iterator();
        while (it1.hasNext()) {
            Order order1 = (Order) it1.next();
            if (order1.checkInterval(System.currentTimeMillis()) && order1.getStatus() == OrderStatus.DONE) {
                it1.remove();
                date = order1.getDateCreate();
                dateOrder.remove(date);
                arrDate.add(date);
            }
        }
        Iterator it2 = orders.iterator();
        while (it2.hasNext()) {
            Order order2 = (Order) it2.next();
            for (Date d : arrDate) {
                if (order2.getDateCreate() == d) {
                    dateOrder.put(d, order2);
                }
            }
        }
    }

    public void showStatusesOfOrders() {
        if (orders.size() == 0) {
            System.out.print("no orders");
        }
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.WAITING) {
                System.out.print("w");
            } else {
                System.out.print("d");
            }
        }
        System.out.println("");
    }

    public void checkTime() {
        if (orders.size() == 0) {
            System.out.println("no orders for checking time");
        }
        for (Order order : orders) {
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
        if (orders.size() == 0) {
            System.out.println("no orders for checking done");
        }
        ArrayList<Date> arrDate = new ArrayList<>();
        Date date;
        Iterator it1 = orders.iterator();
        while (it1.hasNext()) {
            Order order1 = (Order) it1.next();
            if (order1.getStatus() == OrderStatus.DONE) {
                it1.remove();
                date = order1.getDateCreate();
                dateOrder.remove(date);
                arrDate.add(date);
                System.out.print("check done: removing     ");
                showStatusesOfOrders();
            } else {
                System.out.println("check done: no removing");
            }
        }
        Iterator it2 = orders.iterator();
        while (it2.hasNext()) {
            Order order2 = (Order) it2.next();
            for (Date d : arrDate) {
                if (order2.getDateCreate() == d) {
                    dateOrder.put(d, order2);
                }
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