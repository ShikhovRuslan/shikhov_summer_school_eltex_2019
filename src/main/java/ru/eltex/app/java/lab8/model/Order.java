package ru.eltex.app.java.lab8.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
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
    @GeneratedValue
    private UUID id;

    @NotNull
    private OrderStatus status;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date dateCreate;

    @NotNull
    private long timeWaiting;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "cart_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private ShoppingCart cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Credentials user;

    public Order() {

    }

    public Order(Credentials user) {
        //this.cart = cart;
        this.user = user;
    }

    public Order(UUID id, OrderStatus status, Date dateCreate, long timeWaiting, Credentials user) {
        this.id = id;
        this.status = status;
        this.dateCreate = dateCreate;
        this.timeWaiting = timeWaiting;
        //this.cart = cart;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
/*
    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
*/
    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public static Order generate(int type) {
        //ShoppingCart cart = new ShoppingCart(UUID.randomUUID());
        Credentials user = new Credentials(UUID.randomUUID());
        user.create();
        //cart.add(Device.generate(type));
        return new Order(UUID.randomUUID(), OrderStatus.WAITING, new Date(System.currentTimeMillis()), 0, user);
    }

}