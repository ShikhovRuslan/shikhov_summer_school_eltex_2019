package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab2.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    private String id;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Date dateCreate;

    @NotNull
    private long timeWaiting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Credentials user;

    public Order() {
    }

    public Order(OrderStatus status, Date dateCreate, long timeWaiting, Credentials user) {
        id = UUID.randomUUID().toString();
        this.status = status;
        this.dateCreate = dateCreate;
        this.timeWaiting = timeWaiting;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public long getTimeWaiting() {
        return timeWaiting;
    }

    public void setTimeWaiting(long timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

}