package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.lang.reflect.Type;
import java.util.Map;

public class OrdersDeserializer implements JsonDeserializer<Orders> {

    private ManagerOrderJSON moj;

    OrdersDeserializer(ManagerOrderJSON moj) {
        this.moj = moj;
    }

    @Override
    public Orders deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();

        moj.getOrdersTmp().getOrders().clear();
        moj.getOrdersTmp().getDateOrder().clear();
        Order order;

        for (Map.Entry<String, JsonElement> entry : jObject.entrySet()) {
            if (entry.getKey().equals("orders")) {
                JsonElement jElement = entry.getValue();
                JsonArray jArray = jElement.getAsJsonArray();

                for (JsonElement element : jArray) {
                    order = context.deserialize(element, Order.class);
                    moj.getOrdersTmp().add(order);
                }
            }
        }

        return moj.getOrdersTmp();
    }

}