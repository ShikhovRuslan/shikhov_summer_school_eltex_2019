package ru.eltex.app.java.lab8.model;

import ru.eltex.app.java.lab1.TypeSIM;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Smartphone extends Device {

    @NotNull
    private TypeSIM typeSim;

    @NotNull
    private int numberSim;

    public Smartphone() {
    }

    public Smartphone(TypeSIM typeSIM, int numberSim,
                      String name, double price, String firm, String model, String os) {
        super(name, price, firm, model, os);
        this.typeSim = typeSIM;
        this.numberSim = numberSim;
    }

    public TypeSIM getTypeSim() {
        return typeSim;
    }

    public void setTypeSim(TypeSIM typeSim) {
        this.typeSim = typeSim;
    }

    public int getNumberSim() {
        return numberSim;
    }

    public void setNumberSim(int numberSim) {
        this.numberSim = numberSim;
    }

}