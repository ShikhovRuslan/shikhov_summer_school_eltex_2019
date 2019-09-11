package ru.eltex.app.java.lab8.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> products = new LinkedList<>();

    //private Set<UUID> ids;

    public ShoppingCart() {

    }

    public ShoppingCart(UUID id, List<Device> products) {
        this.id = id;
        this.products = products;
        //this.ids = ids;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Device> getProducts() {
        return products;
    }

    public void setProducts(List<Device> products) {
        this.products = products;
    }
/*
    public Set<UUID> getIds() {
        return ids;
    }

    public void setIds(Set<UUID> ids) {
        this.ids = ids;
    }
*/
    public boolean add(Device device) {
        //ids.add(device.getId());
        return products.add(device);
    }

}