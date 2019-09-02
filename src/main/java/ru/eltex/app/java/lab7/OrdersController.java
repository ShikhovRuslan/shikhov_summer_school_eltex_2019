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

    private static final String ERROR1_MESSAGE = "ОШИБКА 1. Заказ не найден!";
    private static final String ERROR2_MESSAGE = "ОШИБКА 2. Файл " + Main.getJsonOrders() + " повреждён!";
    private static final String ERROR3_MESSAGE = "ОШИБКА 3. Неправильная команда!";
    private static final String ERROR3_INPUT_MESSAGE = "ОШИБКА 3. ID введён неправильно!";

    public static String getError1Message() {
        return ERROR1_MESSAGE;
    }

    public static String getError2Message() {
        return ERROR2_MESSAGE;
    }

    private UUID checkingId(String str) throws DelException {
        UUID id;
        try {
            id = UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw new DelException(ERROR3_INPUT_MESSAGE);
        }
        return id;
    }

    private Object showReadAll() throws DelException {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        Orders ordersRead;
        ordersRead = moj.readAll();
        return ordersRead;
    }

    private Object showReadById(UUID orderId) throws DelException {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        Order orderRead;
        orderRead = moj.readById(orderId);
        return orderRead;
    }

    private String showDelById(UUID orderId) throws DelException {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        moj.delById(orderId);
        return "Заказ c ID=" + orderId + " удалён.";
    }

    private Object showAddToCart(UUID cartId) throws DelException {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        UUID idDevice;
        idDevice = moj.addToCart(cartId);
        return idDevice;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command"})
    private Object show1(@RequestParam("command") String command) {
        try {
            switch (command) {
                case ("readall"):
                    return showReadAll();
                default:
                    throw new DelException(ERROR3_MESSAGE);
            }
        } catch (DelException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "order_id"})
    private Object show2(@RequestParam("command") String command, @RequestParam("order_id") String orderIdString) {
        UUID orderId;
        try {
            orderId = checkingId(orderIdString);
            switch (command) {
                case ("readById"):
                    return showReadById(orderId);
                case ("delById"):
                    return showDelById(orderId);
                default:
                    throw new DelException(ERROR3_MESSAGE);
            }
        } catch (DelException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "card_id"})
    private Object show3(@RequestParam("command") String command, @RequestParam("card_id") String cartIdString) {
        UUID cartId;
        try {
            cartId = checkingId(cartIdString);
            switch (command) {
                case ("addToCard"):
                    return showAddToCart(cartId);
                default:
                    throw new DelException(ERROR3_MESSAGE);
            }
        } catch (DelException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String showInvalidCommand() {
        try {
            throw new DelException(ERROR3_MESSAGE);
        } catch (DelException e) {
            return e.getMessage();
        }
    }

}