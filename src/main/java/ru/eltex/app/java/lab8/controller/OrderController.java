package ru.eltex.app.java.lab8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab1.TypePhone;
import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab7.DelException;
import ru.eltex.app.java.lab8.model.*;
import ru.eltex.app.java.lab8.repository.CredentialsRepository;
import ru.eltex.app.java.lab8.repository.DeviceRepository;
import ru.eltex.app.java.lab8.repository.OrderRepository;
import ru.eltex.app.java.lab8.repository.ShoppingCartRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private static final String INFO_MESSAGE = "Checking the command entered by the user. ";
    private static final String ERROR_MESSAGE = "Sorry, something went wrong!";
    private static final String DEL_MESSAGE = "0 - Заказ удалён.";
    private static final Logger logger = Logger.getLogger(ru.eltex.app.java.lab7.OrdersController.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private CredentialsRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    private Object fillInDatabase() {
        List<Credentials> users = new ArrayList<>();
        Credentials user1 = new Credentials(UUID.randomUUID(), "surname1", "name1",
                "patronymic1", "name1@yandex.ru");
        Credentials user2 = new Credentials(UUID.randomUUID(), "surname2", "name2",
                "patronymic2", "name2@yandex.ru");
        users.add(user1);
        users.add(user2);
        userRepository.saveAll(users);

        List<Device> devices = new LinkedList<>();
        Device phone = new Phone(TypePhone.CLASSICAL, UUID.randomUUID(), "name_phone", 14.07, "firm_phone", "model_phone", "OS_phone");
        deviceRepository.saveAndFlush(phone);
        devices.add(phone);

        ShoppingCart cart = new ShoppingCart(UUID.randomUUID(), devices);
        cartRepository.saveAndFlush(cart);

        Order order = new Order(UUID.randomUUID(), OrderStatus.WAITING, new Date(System.currentTimeMillis()), 17L, cart, user1);
        orderRepository.saveAndFlush(order);

        List<Order> orders = orderRepository.findAll();

        return orders;
    }

    private Object getAllOrders() throws DelException {
        List<Order> read = orderRepository.findAll();
        return read;
        //return orderRepository.findAll();
    }

    private Object getAllUsers() throws DelException {
        List<Credentials> read = userRepository.findAll();
        return read;
        //return userRepository.findAll();
    }

    @GetMapping(value = "/", params = {"command"})
    private Object show1(@RequestParam("command") String command) {
        logger.info(INFO_MESSAGE + "\"readall\" or \"fillInDatabase\" is awaited.");
        try {
            switch (command) {
                case ("readall"):
                    return getAllOrders();
                case ("fillInDatabase"):
                    return fillInDatabase();
                case ("readallUsers"):
                    return getAllUsers();
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    /*
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
    */
    @GetMapping("*")
    private String showInvalidCommand() {
        try {
            throw new DelException(3);
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

}