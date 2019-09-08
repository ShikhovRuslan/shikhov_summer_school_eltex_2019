package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab1.Device;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> products;

    private Set<UUID> ids;

    public ShoppingCart() {

    }

    public ShoppingCart(UUID id, List<Device> products, Set<UUID> ids) {
        this.id = id;
        this.products = products;
        this.ids = ids;
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

    public Set<UUID> getIds() {
        return ids;
    }

    public void setIds(Set<UUID> ids) {
        this.ids = ids;
    }

}