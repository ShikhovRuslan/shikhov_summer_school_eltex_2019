package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;
import java.sql.Date;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // создание пользователя
        Credentials user = new Credentials("создание пользователя\n");
        user.create();

        // создание корзины
        ArrayList<Device> products = new ArrayList<>();
        HashSet<UUID> ids = new HashSet<>();
        ShoppingCart<ArrayList<Device>, HashSet<UUID>> gCart = new ShoppingCart<>(products, ids);
        gCart.create();

        ArrayList<Order> orders = new ArrayList<>();
        TreeMap<Date, Order> dateOrder = new TreeMap<>();
        Orders<ArrayList<Order>, TreeMap<Date, Order>> gOrders = new Orders<>(orders, dateOrder);

        // удаление телефона из корзины
        System.out.println("");
        gCart.delete(1);

        // оформление покупки
        gOrders.makePurchase(gCart, user);
        gOrders.show();

        // изменение статуса заказа
        gOrders.getOrders().get(0).setStatus(OrderStatus.DONE);
        gOrders.show();

        // проверка заказов
        gOrders.check();
        gOrders.show();

        // добавление телефона в корзину
        Phone phone = new Phone();
        phone.create();
        gCart.add(phone);
        System.out.println("");
        gCart.show();
    }

}