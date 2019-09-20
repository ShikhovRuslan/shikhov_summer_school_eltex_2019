package ru.eltex.app.java.lab8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import ru.eltex.app.java.lab1.TypePhone;
import ru.eltex.app.java.lab1.TypeSIM;
import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab7.DelException;
import ru.eltex.app.java.lab8.exception.ResourceNotFoundException;
import ru.eltex.app.java.lab8.model.*;
import ru.eltex.app.java.lab8.repository.CredentialsRepository;
import ru.eltex.app.java.lab8.repository.OrderRepository;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private static final String INFO_MESSAGE = "Checking the command entered by the user. ";
    private static final String ERROR_MESSAGE = "Sorry, something went wrong!";
    private static final Logger logger = Logger.getLogger(ru.eltex.app.java.lab8.controller.OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CredentialsRepository userRepository;

    private String fillInDatabase() {
        List<Credentials> users = new ArrayList<>();
        List<Device> devices = new ArrayList<>();
        List<Device> devices1 = new ArrayList<>();
        List<Device> devices2 = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        orderRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        Credentials user1 = new Credentials(
                "surname1", "name1", "patronymic1", "name1@yandex.ru");
        Credentials user2 = new Credentials(
                "surname2", "name2", "patronymic2", "name2@yandex.ru");
        users.add(user1);
        users.add(user2);
        userRepository.saveAll(users);

        Device phone = new Phone(TypePhone.CLASSICAL,
                "name_phone", 14.07, "firm_phone",
                "model_phone", "OS_phone");
        Device smartphone = new Smartphone(TypeSIM.MICRO_SIM, 2,
                "name_smartphone", 28.43, "firm_smartphone",
                "model_smartphone", "OS_smartphone");
        Device tablet = new Tablet("vp", new Dimension(),
                "name_tablet", 28.43, "firm_tablet",
                "model_tablet", "OS_tablet");
        devices.add(phone);
        devices.add(smartphone);
        devices.add(tablet);
        devices1.add(phone);
        devices1.add(tablet);
        devices2.add(phone);
        devices2.add(smartphone);

        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = new ShoppingCart();

        for (Device device : devices1) {
            cart1.add(device);
            device.getCarts().add(cart1);
        }
        for (Device device : devices2) {
            cart2.add(device);
            device.getCarts().add(cart2);
        }

        Order order1 = new Order(OrderStatus.WAITING, new Date(System.currentTimeMillis()), 17L, user1);
        Order order2 = new Order(OrderStatus.WAITING, new Date(System.currentTimeMillis()), 17L, user2);
        order1.setId("00000000-0000-0000-0000-000000000021");

        order1.setCart(cart1);
        order2.setCart(cart2);
        cart1.setOrder(order1);
        cart2.setOrder(order2);

        orders.add(order1);
        orders.add(order2);
        orderRepository.saveAll(orders);

        return "Добавлены заказы в количестве " + orders.size() + " ед.";
    }

    private List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private Optional<Order> getOrderById(String orderId) throws ResourceNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order;
        }
        throw new ResourceNotFoundException("Заказ с ID=" + orderId + " не найден!");
    }

    private String delOrderById(String orderId) throws DelException {
        try {
            orderRepository.deleteById(orderId);
            return "0 - Заказ c ID=" + orderId + " удалён.";
        } catch (EmptyResultDataAccessException e) {
            throw new DelException(1);
        }
    }

    private String addDeviceToCart(String cartId) throws ResourceNotFoundException {
        String deviceId = "";
        List<Order> orders = orderRepository.findAll();
        boolean isDeviceAdded = false;
        for (Order order : orders) {
            if (order.getCart().getId().equals(cartId)) {
                Device device = new Phone(TypePhone.FLIP,
                        "name_new_phone", 79.23, "firm_new_phone",
                        "model_new_phone", "OS_new_phone");
                order.getCart().add(device);
                deviceId = device.getId();
                isDeviceAdded = true;
                break;
            }
        }
        if (isDeviceAdded) {
            orderRepository.saveAll(orders);
            return "Устройство с ID=" + deviceId + " добавлено в корзину с ID=" + cartId;
        }
        throw new ResourceNotFoundException("Корзина c ID=" + cartId + " не найдена!");
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
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    @GetMapping(value = "/", params = {"command", "order_id"})
    private Object show2(@RequestParam("command") String command,
                         @RequestParam("order_id") String orderId) {
        logger.info(INFO_MESSAGE + "\"readById\" or \"delById\" is awaited.");
        try {
            checkId(orderId);
            switch (command) {
                case ("readById"):
                    return getOrderById(orderId);
                case ("delById"):
                    return delOrderById(orderId);
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        } catch (ResourceNotFoundException e) {
            logger.error(ERROR_MESSAGE, e);
            return e.getMessage();
        }
    }

    @GetMapping(value = "/", params = {"command", "card_id"})
    private Object show3(@RequestParam("command") String command,
                         @RequestParam("card_id") String cartId) {
        logger.info(INFO_MESSAGE + "\"addToCard\" is awaited.");
        try {
            checkId(cartId);
            switch (command) {
                case ("addToCard"):
                    return addDeviceToCart(cartId);
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        } catch (ResourceNotFoundException e) {
            logger.error(ERROR_MESSAGE, e);
            return e.getMessage();
        }
    }

    @GetMapping("*")
    private String showInvalidCommand() {
        try {
            throw new DelException(3);
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    private void checkId(String str) throws DelException {
        try {
            UUID id = UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw new DelException(3);
        }
    }

}