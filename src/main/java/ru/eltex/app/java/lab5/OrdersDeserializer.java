package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab3.Orders;

import java.lang.reflect.Type;
import java.util.Map;

public class OrdersDeserializer implements JsonDeserializer<Orders> {

    @Override
    public Orders deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();

        Orders orders = new Orders();
        Order order;

        for (Map.Entry<String, JsonElement> entry : jObject.entrySet()) {
            if (entry.getKey().equals("orders")) {
                JsonElement jElement = entry.getValue();
                JsonArray jArray = jElement.getAsJsonArray();

                for (JsonElement element : jArray) {
                    order = context.deserialize(element, Order.class);
                    orders.add(order);
                }
            }
        }

        return orders;
    }

}