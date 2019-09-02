package ru.eltex.app.java.lab7;

import org.apache.log4j.Logger;
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

    private static final String INFO_MESSAGE = "Checking the command entered by the user. ";
    private static final String ERROR_MESSAGE = "Sorry, something went wrong!";
    private static final String DEL_MESSAGE = "0 - Заказ удалён.";
    private static final Logger logger = Logger.getLogger(OrdersController.class);

    private UUID checkingId(String str) throws DelException {
        UUID id;
        try {
            id = UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw new DelException(3);
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
        return DEL_MESSAGE;
    }

    private Object showAddToCart(UUID cartId) throws DelException {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders(), new Orders<>(new LinkedList<>(), new HashMap<>()));
        UUID idDevice;
        idDevice = moj.addToCart(cartId);
        return idDevice;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command"})
    private Object show1(@RequestParam("command") String command) {
        logger.info(INFO_MESSAGE + "\"readall\" is awaited.");
        try {
            switch (command) {
                case ("readall"):
                    return showReadAll();
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "order_id"})
    private Object show2(@RequestParam("command") String command, @RequestParam("order_id") String orderIdString) {
        UUID orderId;
        logger.info(INFO_MESSAGE + "\"readById\" or \"delById\" is awaited.");
        try {
            orderId = checkingId(orderIdString);
            switch (command) {
                case ("readById"):
                    return showReadById(orderId);
                case ("delById"):
                    return showDelById(orderId);
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "card_id"})
    private Object show3(@RequestParam("command") String command, @RequestParam("card_id") String cartIdString) {
        UUID cartId;
        logger.info(INFO_MESSAGE + "\"addToCard\" is awaited.");
        try {
            cartId = checkingId(cartIdString);
            switch (command) {
                case ("addToCard"):
                    return showAddToCart(cartId);
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String showInvalidCommand() {
        try {
            throw new DelException(3);
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

}