package ru.eltex.app.java.lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Menu(ArrayList<Device> arr, int n) {
        boolean b = true;
        int choice = 0;

        while (b) {
            showActions();
            Scanner sc = new Scanner(System.in);
            boolean h = true;

            System.out.println("Введите команду:");
            while (h) {
                if (!sc.hasNextInt()) {
                    System.out.println("Это не число!");
                    sc.next();
                } else {
                    choice = sc.nextInt();
                    if (choice > 0 && choice < 5) {
                        h = false;
                    } else {
                        System.out.println("Такой команды нет!");
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                switch (choice) {
                    case (1):
                        arr.get(i).read();
                        break;
                    case (2):
                        arr.get(i).update();
                        break;
                    case (3):
                        arr.get(i).delete();
                        break;
                    case (4):
                        b = false;
                        break;
                }
            }
        }
    }

    void showActions() {
        System.out.println("\tВыберите действие:");
        System.out.println("1) вывести данные на экран;");
        System.out.println("2) ввести данные;");
        System.out.println("3) занулить данные;");
        System.out.println("4) выйти.");
    }

}