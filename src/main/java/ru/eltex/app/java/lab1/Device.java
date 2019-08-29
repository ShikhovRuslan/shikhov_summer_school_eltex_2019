package ru.eltex.app.java.lab1;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

// базовый класс
public abstract class Device implements ICrudAction, Serializable {

    private UUID id;              // ID товара
    private String name;          // название
    private double price;         // цена
    private static int count;     // счётчик товаров
    private String firm;          // фирма
    private String model;         // модель
    private String os;            // ОС

    // чтобы вводить данные по умолчанию
    public static <T> T getRandom(T[] arr) {
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }

    public Device() {
        id = UUID.randomUUID();
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

    @Override
    public void create() {
        count++;

        name = getRandom(new String[]{"n1", "n2", "n3"});
        price = getRandom(new Double[]{19.5, 0.02, 445.0});
        firm = getRandom(new String[]{"f1", "f2", "f3"});
        model = getRandom(new String[]{"m1", "m2", "m3"});
        os = getRandom(new String[]{"os1", "os2", "os3"});
    }

    @Override
    public void read() {
        System.out.print("\nсчётчик:\t\t\t " + count + "\nID:\t\t\t\t\t " + id);
        System.out.print("\nимя:\t\t\t\t " + name + "\nцена:\t\t\t\t " + price);
        System.out.print("\nфирма:\t\t\t\t " + firm + "\nмодель:\t\t\t\t " + model + "\nОС:\t\t\t\t\t " + os);
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tВведите данные:");

        System.out.print("имя: ");
        name = sc.nextLine();

        System.out.print("цена: ");
        price = sc.nextDouble();

        sc.nextLine(); // clear your Scanner so you can read the next input

        System.out.print("фирма: ");
        firm = sc.nextLine();

        System.out.print("модель: ");
        model = sc.nextLine();

        System.out.print("ОС: ");
        os = sc.nextLine();
    }

    @Override
    public void delete() {
        if (count == 0) {
            System.out.println("Удаление невозможно, так как нет созданных объектов!");
        } else {
            count--;

            id = UUID.fromString("00000000-0000-0000-0000-000000000000");
            name = "";
            price = 0;
            firm = "";
            model = "";
            os = "";
        }
    }

}