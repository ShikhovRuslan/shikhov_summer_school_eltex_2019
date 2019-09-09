package ru.eltex.app.java.lab8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab7.DelException;
import ru.eltex.app.java.lab8.exception.ResourceNotFoundException;
import ru.eltex.app.java.lab8.model.Credentials;
import ru.eltex.app.java.lab8.model.Order;
import ru.eltex.app.java.lab8.repository.CredentialsRepository;
import ru.eltex.app.java.lab8.repository.OrderRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
public class Controller {

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
                return new ResourceNotFoundException("UserId " + userId + " not found");
            }
        });
    }

    private String fillInDatabase() {
        List<Order> ordersList = new LinkedList<>();

        Order order1 = Order.generate((int) (Math.random() * 3) + 1);
        Order order2 = Order.generate((int) (Math.random() * 3) + 1);
        order2.setId(UUID.fromString("00000000-0000-0000-0000-000000000021"));

        ordersList.add(order1);
        ordersList.add(order2);

        //cartRepository.save(order1.getCart());
        //cartRepository.save(order2.getCart());
        userRepository.save(order1.getUser());
        System.out.println("user " + order1.getUser().getId() + " is added to repository");
        userRepository.save(order2.getUser());
        System.out.println("user " + order2.getUser().getId() + " is added to repository");
        Order ord1 = addOrder(order1.getUser().getId(), order1);
        Order ord2 = addOrder(order2.getUser().getId(), order2);
        orderRepository.saveAll(ordersList);
        return ordersList.size() + " заказов созданы.";
    }

    private Object getAllOrders() throws DelException {
        return orderRepository.findAll();
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