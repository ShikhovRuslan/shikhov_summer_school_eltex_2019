package ru.eltex.app.java.lab8;

import ru.eltex.app.java.lab2.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private UUID id;
    @Column(name = "STATUS")
    private OrderStatus status;
    @Column(name = "DATE_CREATE")
    private Date dateCreate;
    @Column(name = "TIME_WAITING")
    private long timeWaiting;
    @OneToOne
    @JoinColumn(name="SHOPPING_CART_ID")
    private ShoppingCart cart;
    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="CREDENTIALS_ID")
    private Credentials user;

    public Order() {

    }

    public Order(UUID id, OrderStatus status, Date dateCreate, long timeWaiting, ShoppingCart cart, Credentials user) {
        this.id = id;
        this.status = status;
        this.dateCreate = dateCreate;
        this.timeWaiting = timeWaiting;
        this.cart = cart;
        this.user = user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
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

}