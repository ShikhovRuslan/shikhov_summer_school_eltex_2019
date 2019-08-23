package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Order {

    private OrderStatus status;
    private Date dateCreate;
    private long timeWaiting;
    private ShoppingCartGeneric cart;
    private Credentials user;

    public Order() {

    }

    <T extends List<Device>, U extends Set<UUID>> Order(ShoppingCartGeneric<T, U> cart, Credentials user) {
        status = OrderStatus.WAITING;
        dateCreate = new Date(System.currentTimeMillis());
        timeWaiting = (long) (Math.random());
        this.cart = cart;
        this.user = user;
    }

    OrderStatus getStatus() {
        return status;
    }

    void setStatus(OrderStatus status) {
        this.status = status;
    }

    Date getDateCreate() {
        return dateCreate;
    }

    boolean checkInterval(long time) {
        if (dateCreate.getTime() + timeWaiting < time) {
            return true;
        }
        return false;
    }

    void show() {
        System.out.println("статус:\t\t\t" + status);
        System.out.println("дата создания:\t" + dateCreate);
        System.out.println("время ожидания: " + timeWaiting);
        cart.show();
        user.show();
        System.out.println("");
    }

}