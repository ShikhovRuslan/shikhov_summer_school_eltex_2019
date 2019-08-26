package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab2.Credentials;
import ru.eltex.app.java.lab3.Order;
import ru.eltex.app.java.lab2.OrderStatus;
import ru.eltex.app.java.lab3.ShoppingCart;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.UUID;

public class OrderDeserializer implements JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        Order order = new Order();

        order.setStatus(context.deserialize(jObject.get("status"), OrderStatus.class));
        order.setDateCreate(context.deserialize(jObject.get("dateCreate"), Date.class));
        order.setTimeWaiting(jObject.get("timeWaiting").getAsLong());
        order.setCart(context.deserialize(jObject.get("cart"), ShoppingCart.class));
        order.setUser(context.deserialize(jObject.get("user"), Credentials.class));
        order.setId(context.deserialize(jObject.get("id"), UUID.class));

        return order;
    }

}