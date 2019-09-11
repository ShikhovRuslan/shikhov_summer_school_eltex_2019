package ru.eltex.app.java.lab8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab7.DelException;
import ru.eltex.app.java.lab8.exception.ResourceNotFoundException;
import ru.eltex.app.java.lab8.model.Credentials;
import ru.eltex.app.java.lab8.model.Order;
import ru.eltex.app.java.lab8.repository.CredentialsRepository;
import ru.eltex.app.java.lab8.repository.OrderRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
public class OrderController {

    private static final String INFO_MESSAGE = "Checking the command entered by the user. ";
    private static final String ERROR_MESSAGE = "Sorry, something went wrong!";
    private static final String DEL_MESSAGE = "0 - Заказ удалён.";
    private static final Logger logger = Logger.getLogger(ru.eltex.app.java.lab7.OrdersController.class);

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private ShoppingCartRepository cartRepository;

    @Autowired
    private CredentialsRepository userRepository;

    public Order addOrder(UUID userId, Order order) {
        return userRepository.findById(userId).map(new Function<Credentials, Order>() {
            @Override
            public Order apply(Credentials user) {
                order.setUser(user);
                return orderRepository.save(order);
            }
        }).orElseThrow(new Supplier<ResourceNotFoundException>() {
            @Override
            public ResourceNotFoundException get() {
                return new ResourceNotFoundException("Пользователь с ID=" + userId + " не найден!");
            }
        });
    }

    private Object fillInDatabase() {
        Credentials user = new Credentials();
        user.setId(UUID.randomUUID());
        user.setEmail("qw");
        user.setName("wq");
        user.setPatronymic("rerer");
        user.setSurname("rertfd");
        userRepository.saveAndFlush(user);

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setUser(user);
        order.setDateCreate(new Date(System.currentTimeMillis()));
        order.setTimeWaiting(5L);
        order.setStatus(OrderStatus.DONE);
        orderRepository.saveAndFlush(order);

        List<Order> orders = new ArrayList<>();
        orders = orderRepository.findAll();

        return orders;

        /*
        Order order1 = Order.generate((int) (Math.random() * 3) + 1);
        //Order order2 = Order.generate((int) (Math.random() * 3) + 1);
        //order2.setId(UUID.fromString("00000000-0000-0000-0000-000000000021"));

        ordersList.add(order1);
        //ordersList.add(order2);

        //cartRepository.save(order1.getCart());
        //cartRepository.save(order2.getCart());

        for (Order order : ordersList){
            userRepository.saveAndFlush(order.getUser());
        }

        for (Order order : ordersList) {
            //addOrder(order.getUser().getId(), order);
            orderRepository.saveAndFlush(order);
        }

        return ordersList.size() + " заказов созданы.";
        */
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