package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab1.Device;
import ru.eltex.app.java.lab3.ShoppingCart;

import java.lang.reflect.Type;
import java.util.Map;

public class ShoppingCartDeserializer implements JsonDeserializer<ShoppingCart> {

    @Override
    public ShoppingCart deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();

        ShoppingCart cart = new ShoppingCart();
        Device device;

        for (Map.Entry<String, JsonElement> entry : jObject.entrySet()) {
            if (entry.getKey().equals("products")) {
                JsonElement jElement = entry.getValue();
                JsonArray jArray = jElement.getAsJsonArray();

                for (JsonElement element : jArray) {
                    device = context.deserialize(element, Device.class);
                    cart.add(device);
                }
            }
        }

        return cart;
    }

}