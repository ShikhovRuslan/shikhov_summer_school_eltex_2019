package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab2.Order;
import ru.eltex.app.java.lab2.Orders;

import java.util.UUID;

public abstract class AManageOrder implements IOrder {

    protected String filename;

    AManageOrder(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveById(Order order){
        Orders ordersRead = readAll();
        for (Order ord : ordersRead.getOrders()) {
            if (ord.getId() == order.getId()) {
                ordersRead.remove(ord);
            }
        }
        ordersRead.add(order);
        saveAll(ordersRead);
    }

    @Override
    public Order readById(UUID id) {
        Orders ordersRead = readAll();
        Order order = new Order();
        boolean isFound = false;
        for (Order ord : ordersRead.getOrders()) {
            if (ord.getId() == id) {
                order = ord;
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("заказ с ID=" + id + " не найден в файле");
        }
        return order;
    }

}