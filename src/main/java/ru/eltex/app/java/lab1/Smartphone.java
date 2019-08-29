package ru.eltex.app.java.lab1;

import java.util.Scanner;

public class Smartphone extends Device {

    private TypeSIM typeSim; // тип SIM-карты
    private int numberSim;   // количество SIM-карт

    public Smartphone() {

    }

    Smartphone(String message) {
        super();
        System.out.println(message);
    }

    public void setTypeSim(TypeSIM typeSim) {
        this.typeSim = typeSim;
    }

    public TypeSIM getTypeSim() {
        return typeSim;
    }

    public void setNumberSim(int numberSim) {
        this.numberSim = numberSim;
    }

    public int getNumberSim() {
        return numberSim;
    }

    @Override
    public void create() {
        super.create();
        typeSim = TypeSIM.values()[getRandom(new Integer[]{0, 1})];
        numberSim = getRandom(new Integer[]{1, 2, 3});
    }

    @Override
    public void read() {
        super.read();
        System.out.println("\nтип SIM-карты:\t\t " + typeSim + "\nколичество SIM-карт: " + numberSim + "\n");
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        super.update();
        System.out.print("тип SIM-карты (1 - micro-SIM, 2 - обычная): ");
        typeSim = TypeSIM.values()[sc.nextInt() - 1];
        System.out.print("количество SIM-карт: ");
        numberSim = sc.nextInt();
    }

    @Override
    public void delete() {
        super.delete();
        typeSim = TypeSIM.MICRO_SIM; //как занулить это?;
        numberSim = 0;
    }

}