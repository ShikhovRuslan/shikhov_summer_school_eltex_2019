package ru.eltex.app.java.lab8.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

import static ru.eltex.app.java.lab1.Device.getRandom;

@Entity
//@MappedSuperclass
public class Device implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private double price;

    private static int count;

    @NotNull
    private String firm;

    @NotNull
    private String model;

    @NotNull
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

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Device.count = count;
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

    public void create() {
        count++;

        name = getRandom(new String[]{"n1", "n2", "n3"});
        price = getRandom(new Double[]{19.5, 0.02, 445.0});
        firm = getRandom(new String[]{"f1", "f2", "f3"});
        model = getRandom(new String[]{"m1", "m2", "m3"});
        os = getRandom(new String[]{"os1", "os2", "os3"});
    }

    public static Device generate(int type) {
        Device device = null;
        switch (type) {
            case (1):
                device = new Phone();
                break;
            case (2):
                device = new Smartphone();
                break;
            case (3):
                device = new Tablet();
                break;
        }
        device.create();
        device.setId(UUID.randomUUID());
        return device;
    }

}