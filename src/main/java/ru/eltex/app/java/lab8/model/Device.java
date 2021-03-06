package ru.eltex.app.java.lab8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
public abstract class Device implements Serializable {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotBlank
    private String firm;

    @NotBlank
    private String model;

    @NotBlank
    private String os;

    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "products"
    )
    @JsonIgnore
    private List<ShoppingCart> carts = new LinkedList<>();

    public Device() {
    }

    public Device(String name, double price, String firm, String model, String os) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.firm = firm;
        this.model = model;
        this.os = os;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

}