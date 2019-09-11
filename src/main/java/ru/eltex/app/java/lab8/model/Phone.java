package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab1.TypePhone;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "phones")
public class Phone extends Device {

    @NotNull
    private TypePhone type;

    public Phone() {
    }

    public Phone(TypePhone type,
                 UUID id, String name, double price, String firm, String model, String os) {
        super(id, name, price, firm, model, os);
        this.type = type;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

}