package ru.eltex.app.java.lab8;

import ru.eltex.app.java.lab1.Device;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "SHOPPING_CART_ID")
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

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setProducts(List<Device> products) {
        this.products = products;
    }

    public List<Device> getProducts() {
        return products;
    }

    public void setIds(Set<UUID> ids) {
        this.ids = ids;
    }

    public Set<UUID> getIds() {
        return ids;
    }

}