package ru.eltex.app.java.lab5;

import ru.eltex.app.java.lab2.Orders;

import java.io.*;

public class ManagerOrderFile extends AManageOrder {

    ManagerOrderFile(String filename) {
        super(filename);
    }

    @Override
    public void saveAll(Orders orders) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders readAll() {
        Orders ordersRead = new Orders();
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ordersRead = (Orders) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ordersRead;
    }

}