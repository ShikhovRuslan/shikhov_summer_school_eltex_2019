package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

    Menu(ArrayList<ShoppingCart> cartList, ArrayList<Credentials> userList) {
        boolean b = true;
        Orders orders = new Orders();

        while (b) {
            showActions();
            switch (readingCommand()) {
                case (1):
                    orders.makePurchase(cartList.get(choosingCart(cartList) - 1), userList.get(choosingUser(userList) - 1));
                    break;
                case (2):
                    cartList.get(choosingCart(cartList) - 1).show();
                    break;
                case (3):
                    orders.show();
                    break;
                case (4):
                    addCart(cartList);
                    break;
                case (5):
                    cartList.remove(choosingCart(cartList) - 1);
                    break;
                case (6):
                    addToCart(cartList);
                    break;
                case (7):
                    deleteFromCart(cartList);
                    break;
                case (8):
                    addUser(userList);
                    break;
                case (9):
                    userList.remove(choosingUser(userList) - 1);
                    break;
                case (10):
                    orders.check();
                    break;
                case (11):
                    findById(cartList);
                    break;
                case (12):
                    changeStatus(orders);
                    break;
                case (13):
                    b = false;
                    break;
            }
        }
    }

    private void addCart(ArrayList<ShoppingCart> cartList) {
        ShoppingCart cart = new ShoppingCart("добавление корзины");
        cart.create();
        cartList.add(cart);
    }

    private void addToCart(ArrayList<ShoppingCart> cartList) {
        int numberCart = choosingCart(cartList);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер объекта (1 - телефон, 2 - смартфон, 3 - планшет):");
        switch (sc.nextInt()) {
            case (1):
                cartList.get(numberCart - 1).add(new Phone());
                break;
            case (2):
                cartList.get(numberCart - 1).add(new Smartphone());
                break;
            case (3):
                cartList.get(numberCart - 1).add(new Tablet());
                break;
        }
        cartList.get(numberCart - 1).getLast().create();
    }

    private void deleteFromCart(ArrayList<ShoppingCart> cartList) {
        int numberCart = choosingCart(cartList);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите тип объекта (1 - телефон, 2 - смартфон, 3 - планшет):");
        cartList.get(numberCart - 1).delete(sc.nextInt());
    }

    private void addUser(ArrayList<Credentials> userList) {
        Credentials user = new Credentials("добавление пользователя");
        user.update();
        userList.add(user);
    }

    private void findById(ArrayList<ShoppingCart> cartList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите идентификатор объекта для поиска в корзинах:");
        UUID id = UUID.fromString(sc.nextLine());
        boolean found = false;
        for (ShoppingCart cart : cartList) {
            if (cart.search(id)) {
                System.out.println("Данный объект найден в корзине " + (cartList.indexOf(cart) + 1));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Ни в одной корзине данный объект не найден!");
        }
    }

    private void changeStatus(Orders orders) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите заказ, статус которого вы хотите изменить (их всего " + orders.getOrders().size() + "):");
        orders.getOrders().get(sc.nextInt() - 1).setStatus(OrderStatus.DONE);
    }

    private int readingCommand() {
        boolean h = true;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду:");
        while (h) {
            if (!sc.hasNextInt()) {
                System.out.println("Это не число!");
                sc.next();
            } else {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 13) {
                    h = false;
                } else {
                    System.out.println("Такой команды нет!");
                }
            }
        }
        return choice;
    }

    private int choosingCart(ArrayList<ShoppingCart> cartList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите корзину (их всего " + cartList.size() + "):");
        return sc.nextInt();
    }

    private int choosingUser(ArrayList<Credentials> userList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите пользователя (их всего " + userList.size() + "):");
        return sc.nextInt();
    }

    private void showActions() {
        System.out.println("\n\tВыберите действие:");
        System.out.println(" 1) оформить покупку;");
        System.out.println(" 2) показать содержимое корзины;");
        System.out.println(" 3) показать все заказы;");
        System.out.println(" 4) добавить корзину;");
        System.out.println(" 5) удалить корзину;");
        System.out.println(" 6) добавить объект в корзину;");
        System.out.println(" 7) удалить объект из корзины;");
        System.out.println(" 8) добавить пользователя;");
        System.out.println(" 9) удалить пользователя;");
        System.out.println("10) проверить заказы;");
        System.out.println("11) найти объект в корзинах по идентификатору;");
        System.out.println("12) изменить статус заказа;");
        System.out.println("13) выйти.");
    }

}