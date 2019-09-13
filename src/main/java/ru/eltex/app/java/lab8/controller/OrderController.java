package ru.eltex.app.java.lab8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eltex.app.java.lab1.TypePhone;
import ru.eltex.app.java.lab1.TypeSIM;
import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab7.DelException;
import ru.eltex.app.java.lab8.model.*;
import ru.eltex.app.java.lab8.repository.CredentialsRepository;
import ru.eltex.app.java.lab8.repository.DeviceRepository;
import ru.eltex.app.java.lab8.repository.OrderRepository;
import ru.eltex.app.java.lab8.repository.ShoppingCartRepository;

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

    private UUID checkingId(String str) throws DelException {
        UUID id;
        try {
            id = UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw new DelException(3);
        }
        return id;
    }

    private Object fillInDatabase() {
        List<Credentials> users = new ArrayList<>();
        List<Device> devices = new ArrayList<>();
        List<Device> devices1 = new ArrayList<>();
        List<Device> devices2 = new ArrayList<>();
        List<ShoppingCart> carts = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        Credentials user1 = new Credentials(
                "surname1", "name1", "patronymic1", "name1@yandex.ru");
        Credentials user2 = new Credentials(
                "surname2", "name2", "patronymic2", "name2@yandex.ru");
        users.add(user1);
        users.add(user2);
        userRepository.saveAll(users);

        Device phone = new Phone(TypePhone.CLASSICAL,
                "name_phone", 14.07, "firm_phone",
                "model_phone", "OS_phone", new ArrayList<>());
        Device smartphone = new Smartphone(TypeSIM.MICRO_SIM, 2,
                "name_smartphone", 28.43, "firm_smartphone",
                "model_smartphone", "OS_smartphone", new ArrayList<>());
        Device tablet = new Tablet("vp", new Dimension(),
                "name_tablet", 28.43, "firm_tablet",
                "model_tablet", "OS_tablet", new ArrayList<>());
        devices.add(phone);
        devices.add(smartphone);
        devices.add(tablet);
        devices1.add(phone);
        devices1.add(tablet);
        devices2.add(phone);
        devices2.add(smartphone);
        deviceRepository.saveAll(devices);

        ShoppingCart cart1 = new ShoppingCart(new ArrayList<Device>());
        ShoppingCart cart2 = new ShoppingCart(new ArrayList<Device>());
        for (Device device : devices1) {
            cart1.getProducts().add(phone);
            phone.getCarts().add(cart1);
        }
        for (Device device : devices2) {
            cart2.getProducts().add(device);
            device.getCarts().add(cart2);
        }
        carts.add(cart1);
        carts.add(cart2);
        cartRepository.saveAll(carts);

        Order order1 = new Order(OrderStatus.WAITING, new Date(System.currentTimeMillis()), 17L, cart1, user1);
        Order order2 = new Order(OrderStatus.WAITING, new Date(System.currentTimeMillis()), 17L, cart2, user2);
        orders.add(order1);
        orders.add(order2);
        orderRepository.saveAll(orders);

        deviceRepository.flush();
        cartRepository.flush();


        //return "Добавленные заказы: " + orders.size() + " ед.";
        System.out.println("\n\n --------------------part 1----------------------\n\n");
        return orderRepository.findAll();

    }

    private Object getAllOrders() {
        System.out.println("\n\n --------------------part 2----------------------\n\n");
        List<Device> devices = deviceRepository.findAll();
        List<ShoppingCart> carts = cartRepository.findAll();
        List<Credentials> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        List<Order> ordersRead = orderRepository.findAll();
        return ordersRead;
    }

    private Object getById(UUID orderId) {
        deviceRepository.findAll();
        cartRepository.findAll();
        userRepository.findAll();
        orderRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        Optional<Order> order = orderRepository.findById(orderId);
        return order;
    }

    /*
        private String delById(UUID orderId){
            return orderRepository.delById(orderId);
        }
    */
    @GetMapping(value = "/", params = {"command"})
    public Object show1(@RequestParam("command") String command) {
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

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "order_id"})
    private Object show2(@RequestParam("command") String command, @RequestParam("order_id") String orderIdString) {
        UUID orderId;
        logger.info(INFO_MESSAGE + "\"readById\" or \"delById\" is awaited.");
        try {
            orderId = checkingId(orderIdString);
            switch (command) {
                case ("readById"):
                    return getById(orderId);
                //case ("delById"):
                //    return delById(orderId);
                default:
                    throw new DelException(3);
            }
        } catch (DelException e) {
            logger.error(ERROR_MESSAGE, e);
            return "Код ошибки - " + e.getErrorCode() + ". " + e.getMessage();
        }
    }

    /*
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