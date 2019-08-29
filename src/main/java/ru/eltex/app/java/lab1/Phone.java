package ru.eltex.app.java.lab1;

import java.util.Scanner;

public class Phone extends Device {

    private TypePhone type; // тип корпуса

    public Phone() {

    }

    Phone(String message) {
        super();
        System.out.println(message);
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

    public TypePhone getType() {
        return type;
    }

    @Override
    public void create() {
        super.create();
        type = TypePhone.values()[getRandom(new Integer[]{0, 1})];
    }

    @Override
    public void read() {
        super.read();
        System.out.println("\nтип корпуса:\t\t " + type + "\n");
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        super.update();
        System.out.print("тип корпуса (1 - классический, 2 - раскладушка): ");
        type = TypePhone.values()[sc.nextInt() - 1];
    }

    @Override
    public void delete() {
        super.delete();
        type = TypePhone.CLASSICAL;
    }

}