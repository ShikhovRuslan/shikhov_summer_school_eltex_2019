package ru.eltex.app.java.lab2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<ShoppingCart> cartList = new ArrayList<>();
        ArrayList<Credentials> userList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите число создаваемых по умолчанию корзин:");
        int amountCarts = sc.nextInt();
        System.out.println("Введите число создаваемых по умолчанию пользователей:");
        int amountUsers = sc.nextInt();

        for (int i = 0; i < amountCarts; i++) {
            ShoppingCart cart = new ShoppingCart("создание корзины");
            cart.create();
            cartList.add(cart);
        }
        for (int i = 0; i < amountUsers; i++) {
            Credentials user = new Credentials("создание пользователя");
            user.create();
            userList.add(user);
        }

        Menu menu = new Menu(cartList, userList);
    }

}