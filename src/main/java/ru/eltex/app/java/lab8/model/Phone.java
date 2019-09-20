package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab1.TypePhone;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Phone extends Device {

    @NotNull
    private TypePhone type;

    public Phone() {
    }

    public Phone(TypePhone type,
                 String name, double price, String firm, String model, String os, List<ShoppingCart> carts) {
        super(name, price, firm, model, os, carts);
        this.type = type;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

}