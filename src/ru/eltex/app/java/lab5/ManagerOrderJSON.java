package ru.eltex.app.java.lab5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.Order;
import ru.eltex.app.java.lab2.Orders;
import ru.eltex.app.java.lab2.ShoppingCart;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class ManagerOrderJSON extends AManageOrder {

    ManagerOrderJSON(String filename) {
        super(filename);
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
                .registerTypeAdapter(Orders.class, new OrdersDeserializer())
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

}