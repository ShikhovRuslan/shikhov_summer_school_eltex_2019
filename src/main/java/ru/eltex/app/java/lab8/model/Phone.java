package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab1.TypePhone;

import javax.validation.constraints.NotNull;

public class Phone extends Device {

    @NotNull
    private TypePhone type;

    public Phone() {

    }

    public Phone(TypePhone type) {
        super();
        this.type = type;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

}