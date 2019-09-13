package ru.eltex.app.java.lab8.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TABLE_SHOPPING_CARTS")
public class ShoppingCart implements Serializable {

    @Id
    private String id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                    //CascadeType.ALL
            })
    @JoinTable(name = "TABLE_CART_DEVICES",
            joinColumns = {@JoinColumn(name = "CARTTT_IDDD")},
            inverseJoinColumns = {@JoinColumn(name = "DEVICEEE_IDDD")})
    private List<Device> products;

    //private Set<UUID> ids;

    public ShoppingCart() {
    }

    public ShoppingCart(List<Device> products) {
        id = UUID.randomUUID().toString();
        this.products = products;
        //this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void addDevice(Device device) {
        products.add(device);
        device.getCarts().add(this);
    }

    public void removeDevice(Device device) {
        products.remove(device);
        device.getCarts().remove(this);
    }

}