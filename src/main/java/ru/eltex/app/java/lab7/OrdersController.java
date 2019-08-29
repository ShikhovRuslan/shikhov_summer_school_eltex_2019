package ru.eltex.app.java.lab7;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.app.java.lab3.Orders;

@RestController
public class OrdersController {

    static final String FILE_ORDERS = "/home/ruslan/summer_school_2019/labs_java/orders.ser";

    @RequestMapping("/")
    public Orders showReadAll(@RequestParam(value="command") String command) {
        OrdersContainer oc = new OrdersContainer();
        Orders orders = oc.getOrders();
        return orders;

//        ManagerOrderFile moj = new ManagerOrderFile(FILE_ORDERS);
//        moj.saveAll(orders);
////        order2ReadMoj = moj.readById(order2.getId());
////        order2ReadMoj.show();
////        orderRandomReadMoj = moj.readById(idRandom);
////        System.out.println("\n\n");
////        moj.saveById(orderInstead1);
////        moj.saveById(orderWithNewId);
//        Orders ordersReadMoj = moj.readAll();
//        return ordersReadMoj;
    }

}