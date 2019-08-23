package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Credentials user = new Credentials("создание пользователя");
        user.create();

//        LinkedList<Device> products = new LinkedList<>();
//        HashSet<UUID> ids = new HashSet<>();
//        ShoppingCartGeneric<LinkedList<Device>, HashSet<UUID>> cartGeneric = new ShoppingCartGeneric<>(products, ids);
//        cartGeneric.create();

        ArrayList<Device> products = new ArrayList<>();
        HashSet<UUID> ids = new HashSet<>();
        ShoppingCartGeneric<ArrayList<Device>, HashSet<UUID>> cartGeneric = new ShoppingCartGeneric<>(products, ids);
        cartGeneric.create();

        ArrayList<Order> orders = new ArrayList<>();
        TreeMap<Date, Order> dateOrder = new TreeMap<>();
        OrdersGeneric<ArrayList<Order>, TreeMap<Date, Order>> ordersGeneric = new OrdersGeneric<>(orders, dateOrder);

        cartGeneric.delete(1);

        ordersGeneric.makePurchase(cartGeneric, user);
        ordersGeneric.show();
        ordersGeneric.getOrders().get(0).setStatus(OrderStatus.DONE);
        ordersGeneric.show();
        ordersGeneric.check();
        ordersGeneric.show();

        Phone phone = new Phone();
        cartGeneric.add(phone);
        cartGeneric.show();

//        LinkedList<Device> products2 = new LinkedList<>();
//        HashSet<UUID> ids2 = new HashSet<>();
//        ShoppingCartGeneric<LinkedList<Device>, HashSet<UUID>> cartGeneric2 = new ShoppingCartGeneric<>(products2, ids2);
//        cartGeneric.create();
    }

}