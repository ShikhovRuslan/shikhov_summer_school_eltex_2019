package ru.eltex.app.java.lab7;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab5.Main;
import ru.eltex.app.java.lab5.ManagerOrderJSON;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class OrdersController {

    private static final String ERROR_MESSAGE = "ERROR: wrong value of command!";

    private Orders showReadAll() {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        return moj.readAll();
    }

    private Order showReadById(UUID orderId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        return moj.readById(orderId);
    }

    private int showDelById(UUID orderId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        try {
            return moj.delById(orderId);
        } catch (DelException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    private UUID showAddToCart(UUID cartId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        return moj.addToCart(cartId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command"})
    private Object show1(@RequestParam("command") String command) {
        if (command.equals("readall")) {
            return showReadAll();
        } else {
            return ERROR_MESSAGE;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "order_id"})
    private Object show2(@RequestParam("command") String command, @RequestParam("order_id") UUID orderId) {
        switch (command) {
            case ("readById"):
                return showReadById(orderId);
            case ("delById"):
                return showDelById(orderId);
            default:
                return ERROR_MESSAGE;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "card_id"})
    private Object show3(@RequestParam("command") String command, @RequestParam("card_id") UUID cartId) {
        if (command.equals("addToCard")) {
            return showAddToCart(cartId);
        } else {
            return ERROR_MESSAGE;
        }
    }

}