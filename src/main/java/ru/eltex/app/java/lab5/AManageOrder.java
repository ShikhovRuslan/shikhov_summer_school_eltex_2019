package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab7.DelException;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class AManageOrder implements IOrder {

    protected String filename;

    AManageOrder(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveById(Order order) {
        Orders ordersRead = null;
        try {
            ordersRead = readAll();
        } catch (DelException e) {
            System.out.println(e.getMessage());
        }
        Date date = null;
        Iterator it1 = ordersRead.getOrders().iterator();
        while (it1.hasNext()) {
            Order order1 = (Order) it1.next();
            if (order1.getId().equals(order.getId())) {
                it1.remove();
                date = order1.getDateCreate();
            }
        }
        Iterator it2 = ordersRead.getOrders().iterator();
        while (it2.hasNext()) {
            Order order2 = (Order) it2.next();
            if (order2.getDateCreate() == date) {
                ordersRead.getDateOrder().put(date, order2);
            }
        }
        ordersRead.add(order);
        saveAll(ordersRead);
    }

    @Override
    public Order readById(UUID id) throws DelException {
        Orders ordersRead;
        ordersRead = readAll();
        Order order = new Order();
        boolean isFound = false;
        for (Order ord : (List<Order>) ordersRead.getOrders()) {
            if (ord.getId().equals(id)) {
                order = ord;
                isFound = true;
                System.out.println("заказ с ID=" + id + " найден в файле\n");
                break;
            }
        }
        if (!isFound) {
            System.out.println("заказ с ID=" + id + " не найден в файле\n");
        }
        return order;
    }

}