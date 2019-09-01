package ru.eltex.app.java.lab5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab3.ShoppingCart;
import ru.eltex.app.java.lab7.DelException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

public class ManagerOrderJSON extends AManageOrder {

    private Orders ordersTmp;

    public ManagerOrderJSON(String filename, Orders ordersTmp) {
        super(filename);
        this.ordersTmp = ordersTmp;
    }

    Orders getOrdersTmp() {
        return ordersTmp;
    }

    @Override
    public void saveAll(Orders orders) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders readAll() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Orders.class, new OrdersDeserializer(this))
                .registerTypeAdapter(Order.class, new OrderDeserializer())
                .registerTypeAdapter(ShoppingCart.class, new ShoppingCartDeserializer())
                .registerTypeAdapter(Credentials.class, new CredentialsDeserializer())
                .registerTypeAdapter(Device.class, new DeviceDeserializer())
                .create();
        Orders ordersRead = new Orders();
        try {
            Reader reader = new FileReader(filename);
            ordersRead = gson.fromJson(reader, Orders.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordersRead;
    }

    public int delById(UUID idOrder) throws DelException {
        Orders ordersRead = readAll();
        int error = ordersRead.delById(idOrder);
        if (error == 1) throw new DelException("Заказ с ID=" + idOrder + " не найден!");
        saveAll(ordersRead);
        return error;
    }

    public UUID addToCart(UUID idCart) {
        Orders ordersRead = readAll();
        UUID idDevice = ordersRead.addToCart(idCart);
        saveAll(ordersRead);
        return idDevice;
    }

}