package ru.eltex.app.java.lab7;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;
import ru.eltex.app.java.lab5.Main;
import ru.eltex.app.java.lab5.ManagerOrderJSON;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class OrdersController {

//    @RequestMapping("/")
//    public Orders showReadAll(@RequestParam(value="command") String command) {
//        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders());
//        return moj.readAll();
//    }
//
//    // /?command=readById&order_id=[id]
//    @RequestMapping("/")
//    public Order showReadById(@RequestParam(value="command") String command, @RequestParam(value="order_id") String orderId){
//        return new Order();
//    }
/*
    //@RequestMapping("/")
    public Order showReadById(@RequestParam(value = "command") String command, @RequestParam(value = "order_id") int id) {
        return new Order();
    }
*/
    /*@RequestMapping("/")
    @ResponseBody
    public Object show(@RequestParam(value = "command") String command, @RequestParam(value = "order_id") int id) {
        switch(command){
            case ("readById"):
                return "hi there";
            case ("delById"):
                return new Order();
            default:
                return "wrong command!";
        }*/

//        if (command.equals("readById")) {
//            return "hi there";
//        } else {
//            if (command.equals("delById")) {
//                return new Order();
//            } else {
//                return "---gfgfgf";
//            }
//        }

/*
    @RequestMapping("/")
    @ResponseBody
    public Object show2(@RequestParam(value = "card_id") int id) {
        switch (id) {
            case (1):
                return "hi there";
            case (2):
                return new Order();
            default:
                return "wrong command!";
        }
    }
*/

    private Orders showReadAll() {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders());
        return moj.readAll();
    }

    private Order showReadById(UUID orderId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders());
        return moj.readById(orderId);
    }

    private int showDelById(UUID orderId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders());
        return moj.delById(orderId);
    }

    private UUID showAddToCart(UUID cartId) {
        ManagerOrderJSON moj = new ManagerOrderJSON(Main.getJsonOrders());
        return moj.addToCart(cartId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command"})
    public Object show1(@RequestParam("command") String command) {
        if(command.equals("readall")){
            return showReadAll();
        } else {
            return "ERROR: wrong value of command!";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "order_id"})
    public Object show2(@RequestParam("command") String command, @RequestParam("order_id") UUID orderId) {
        switch(command){
            case("readById"):
                return showReadById(orderId);
            case("delById"):
                return showDelById(orderId);
            default:
                return "ERROR: wrong value of command!";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"command", "card_id"})
    public Object show3(@RequestParam("command") String command, @RequestParam("card_id") UUID cartId) {
        if(command.equals("addToCard")){
            return showAddToCart(cartId);
        } else {
            return "ERROR: wrong value of command!";
        }
    }

/*
    @RequestMapping("/")
    @ResponseBody
    public Object readCommand(@RequestParam Map<String,String> allParams) {
        Order order = new Order();

        String command = allParams.get("command");
        UUID orderId = UUID.fromString(allParams.get("order_id"));
        UUID cartId = UUID.fromString(allParams.get("card_id"));
        switch (command) {
            case ("readall"):
                order = showReadAll();
                break;
//            case("readById"):
//                showReadById(orderId);
//                break;
//            case("addToCard"):
//                showAddToCart(cartId);
//                break;
//            case("delById"):
//                showDelById(orderId);
//                break;
        }
        return order;
    }*/

}