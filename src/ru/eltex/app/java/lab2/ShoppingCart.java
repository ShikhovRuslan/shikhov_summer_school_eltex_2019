package ru.eltex.app.java.lab2;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;

import java.io.Serializable;
import java.util.*;

public class ShoppingCart implements Serializable {

    private List<Device> products;
    private Set<UUID> ids;

    public ShoppingCart(String message) {
        products = new LinkedList<>();
        ids = new HashSet<>();
        System.out.println(message);
    }

    public ShoppingCart() {
        products = new LinkedList<>();
        ids = new HashSet<>();
    }

    Device getLast() {
        return products.get(products.size() - 1);
    }

    boolean search(UUID id) {
        return ids.contains(id);
    }

    public boolean add(Device device) {
        ids.add(device.getId());
        return products.add(device);
    }

    void create() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите подряд номера устройств, которые будут лежать в корзине ");
        System.out.println("(1 - телефон, 2 - смартфон, 3 - планшет):");
        String request = sc.nextLine();
        boolean isPhoneAdded = false;
        boolean isSmartphoneAdded = false;
        boolean isTabletAdded = false;
        for (char symbol : request.toCharArray()) {
            switch (symbol) {
                case ('1'):
                    if (!isPhoneAdded) {
                        add(new Phone());
                        isPhoneAdded = true;
                    } else {
                        System.out.println("Телефон уже добавлен!");
                    }
                    break;
                case ('2'):
                    if (!isSmartphoneAdded) {
                        add(new Smartphone());
                        isSmartphoneAdded = true;
                    } else {
                        System.out.println("Смартфон уже добавлен!");
                    }
                    break;
                case ('3'):
                    if (!isTabletAdded) {
                        add(new Tablet());
                        isTabletAdded = true;
                    } else {
                        System.out.println("Планшет уже добавлен!");
                    }
                    break;
            }
            getLast().create();
        }
    }

    /**
     * Удаление из корзины
     *
     * @param type Тип объекта, который будет удалён из корзины
     */
    void delete(int type) {
        boolean isDeleted = false;
        Iterator it = products.iterator();
        while (it.hasNext() && !isDeleted) {
            switch (type) {
                case (1):
                    if (it.next() instanceof Phone) {
                        it.remove();
                        isDeleted = true;
                    }
                    break;
                case (2):
                    if (it.next() instanceof Smartphone) {
                        it.remove();
                        isDeleted = true;
                    }
                    break;
                case (3):
                    if (it.next() instanceof Tablet) {
                        it.remove();
                        isDeleted = true;
                    }
                    break;
            }
        }
        if (!isDeleted) {
            System.out.println("Объект, подлежащий удалению, не найден!");
        }
    }

    void show() {
        System.out.println("----------данные корзины-----------");
        for (Device device : products) {
            device.read();
        }
    }

}