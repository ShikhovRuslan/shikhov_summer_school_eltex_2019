package ru.eltex.app.java.lab2;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

public class Order implements Serializable {

    private OrderStatus status;
    private Date dateCreate;
    private long timeWaiting;
    private ShoppingCart cart;
    private Credentials user;
    private UUID id;

    public Order() {

    }

    public Order(ShoppingCart cart, Credentials user) {
        status = OrderStatus.WAITING;
        dateCreate = new Date(System.currentTimeMillis());
        timeWaiting = (long) (Math.random() * 20000);
        this.cart = cart;
        this.user = user;
        id = UUID.randomUUID();
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setTimeWaiting(long timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public UUID getId() {
        return id;
    }

    public boolean checkInterval(long time) {
        if (dateCreate.getTime() + timeWaiting < time) {
            return true;
        }
        return false;
    }

    public void show() {
        System.out.println("статус:\t\t\t" + status);
        System.out.println("дата создания:\t" + dateCreate);
        System.out.println("время ожидания: " + timeWaiting);
        System.out.println("ID:\t\t\t\t" + id);
        cart.show();
        user.show();
        System.out.println("");
    }

    public void showShort() {
        System.out.println(id);
    }

}