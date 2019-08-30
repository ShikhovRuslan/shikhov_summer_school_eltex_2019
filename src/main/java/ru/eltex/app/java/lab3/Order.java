package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab2.OrderStatus;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Order implements Serializable {

    private OrderStatus status;
    private Date dateCreate;
    private long timeWaiting;
    private ShoppingCart cart;
    private Credentials user;
    private UUID id;

    public Order() {

    }

    public <T extends List<Device>, U extends Set<UUID>> Order(ShoppingCart<T, U> cart, Credentials user) {
        status = OrderStatus.WAITING;
        dateCreate = new Date(System.currentTimeMillis());
        timeWaiting = (long) (Math.random() * 3);
        this.cart = cart;
        this.user = user;
        id = UUID.randomUUID();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setTimeWaiting(long timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    public long getTimeWaiting() {
        return timeWaiting;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public Credentials getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static Order generate(int type) {
        ShoppingCart<LinkedList<Device>, HashSet<UUID>> cart = new ShoppingCart<>();
        Credentials user = new Credentials();
        user.create();
        cart.add(Device.generate(type));
        return new Order(cart, user);
    }

    boolean checkInterval(long time) {
        if (dateCreate.getTime() + timeWaiting < time) {
            return true;
        }
        return false;
    }

    public void show() {
        System.out.println("статус:\t\t\t" + status);
        System.out.println("дата создания:\t" + dateCreate);
        System.out.println("время ожидания: " + timeWaiting);
        cart.show();
        user.show();
        System.out.println("");
    }

}