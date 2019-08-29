package ru.eltex.app.java.lab3;

import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;

import java.io.Serializable;
import java.util.*;

public class ShoppingCart<T extends List<Device>, U extends Set<UUID>> implements Serializable {

    private T products;
    private U ids;

    public ShoppingCart() {
        List<Device> products = new LinkedList<>();
        Set<UUID> ids = new HashSet<>();
        this.products = (T) products;
        this.ids = (U) ids;
    }

    public ShoppingCart(T products, U ids) {
        this.products = products;
        this.ids = ids;
        for (Device device : products) {
            ids.add(device.getId());
        }
    }

    public void setProducts(T products) {
        this.products = products;
    }

    public T getProducts() {
        return products;
    }

    public void setIds(U ids) {
        this.ids = ids;
    }

    public U getIds() {
        return ids;
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
        Device device;
        Iterator it = products.iterator();
        while (it.hasNext() && !isDeleted) {
            device = (Device) it.next();
            switch (type) {
                case (1):
                    if (device instanceof Phone) {
                        it.remove();
                        ids.remove(device.getId());
                        isDeleted = true;
                    }
                    break;
                case (2):
                    if (device instanceof Smartphone) {
                        it.remove();
                        ids.remove(device.getId());
                        isDeleted = true;
                    }
                    break;
                case (3):
                    if (device instanceof Tablet) {
                        it.remove();
                        ids.remove(device.getId());
                        isDeleted = true;
                    }
                    break;
            }
        }
        if (!isDeleted) {
            System.out.println("Объект, подлежащий удалению, не найден!\n");
        }
    }

    void show() {
        System.out.println("----------данные корзины-----------");
        for (Device device : products) {
            device.read();
        }
    }

}