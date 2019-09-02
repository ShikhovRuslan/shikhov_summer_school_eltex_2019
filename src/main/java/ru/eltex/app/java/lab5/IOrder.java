package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab7.DelException;

import java.util.UUID;

public interface IOrder {

    Order readById(UUID id) throws DelException;

    void saveById(Order order);

    Orders readAll() throws DelException;

    void saveAll(Orders orders);

}