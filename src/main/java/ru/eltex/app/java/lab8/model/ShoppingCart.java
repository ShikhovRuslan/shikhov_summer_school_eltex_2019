package ru.eltex.app.java.lab8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart implements Serializable {

    @Id
    private String id;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "carts_devices",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id")}
    )
    private List<Device> products = new LinkedList<>();

    @ElementCollection(targetClass = String.class)
    private Set<String> ids = new HashSet<>();

    public ShoppingCart() {
    }

    public ShoppingCart(List<Device> devices) {
        id = UUID.randomUUID().toString();
        for (Device device : devices) {
            add(device);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Device> getProducts() {
        return products;
    }

    public void setProducts(List<Device> products) {
        this.products = products;
    }

    public Set<String> getIds() {
        return ids;
    }

    public void setIds(Set<String> ids) {
        this.ids = ids;
    }

    public boolean add(Device device) {
        ids.add(device.getId());
        return products.add(device);
    }

}