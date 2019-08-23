package ru.eltex.app.java.lab1;

import ru.eltex.app.java.lab1.Phone;
import ru.eltex.app.java.lab1.Smartphone;
import ru.eltex.app.java.lab1.Tablet;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Device> device = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            switch (args[i + 1]) {
                case ("1"):
                    device.add(i, new Phone("создание телефона"));
                    device.get(i).create();
                    break;
                case ("2"):
                    device.add(i, new Smartphone("создание смартфона"));
                    device.get(i).create();
                    break;
                case ("3"):
                    device.add(i, new Tablet("создание планшета"));
                    device.get(i).create();
                    break;
            }
        }

        Menu menu = new Menu(device, Integer.parseInt(args[0]));
    }

}