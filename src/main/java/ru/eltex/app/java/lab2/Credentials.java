package ru.eltex.app.java.lab2;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Credentials implements Serializable {

    private UUID id;
    private String surname;
    private String name;
    private String patronymic;
    private String email;

    public Credentials(String message) {
        id = UUID.randomUUID();
        System.out.println(message);
    }

    public Credentials() {
        id = UUID.randomUUID();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    static <T> T getRandom(T[] arr) {
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }

    public void create() {
        surname = getRandom(new String[]{"surname1", "surname2", "surname3"});
        name = getRandom(new String[]{"name1", "name2", "name3"});
        patronymic = getRandom(new String[]{"patronymic1", "patronymic2", "patronymic3"});
        email = getRandom(new String[]{"email1", "email2", "email3"});
    }

    void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tВведите данные пользователя:");

        System.out.print("фамилия: ");
        surname = sc.nextLine();

        System.out.print("имя: ");
        name = sc.nextLine();

        System.out.print("отчество: ");
        patronymic = sc.nextLine();

        System.out.print("эл. почта: ");
        email = sc.nextLine();
    }

    public void show() {
        System.out.println("--------данные пользователя--------");
        System.out.println("ID:\t\t\t" + id);
        System.out.println("фамилия:\t" + surname);
        System.out.println("имя:\t\t" + name);
        System.out.println("отчество:\t" + patronymic);
        System.out.println("эл. почта:\t" + email);
    }

}