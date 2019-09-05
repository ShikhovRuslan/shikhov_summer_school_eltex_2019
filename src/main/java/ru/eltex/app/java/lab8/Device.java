package ru.eltex.app.java.lab8;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "DEVICE")
public class Device implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "DEVICE_ID")
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private double price;
    private static int count;
    @Column(name = "FIRM")
    private String firm;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "OS")
    private String os;

    public Device() {

    }

    public Device(UUID id, String name, double price, int count, String firm, String model, String os) {
        this.id = id;
        this.name = name;
        this.price = price;
        Device.count = count;
        this.firm = firm;
        this.model = model;
        this.os = os;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public static void setCount(int count) {
        Device.count = count;
    }

    public static int getCount() {
        return count;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getFirm() {
        return firm;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs() {
        return os;
    }

}