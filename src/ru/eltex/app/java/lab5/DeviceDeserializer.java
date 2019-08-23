package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab1.*;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class DeviceDeserializer implements JsonDeserializer<Device> {

    int identifyTypeOfDevice(JsonObject jObj) {
        int numberType = 0;
        for (Map.Entry<String, JsonElement> ent : jObj.entrySet()) {
            if (ent.getKey().equals("type")) {
                numberType = 1;
                break;
            }
            if (ent.getKey().equals("typeSim")) {
                numberType = 2;
                break;
            }
            if (ent.getKey().equals("videoProcessor")) {
                numberType = 3;
                break;
            }
        }
        return numberType;
    }

    Device addSpecific(int numberType, JsonObject jObj, JsonDeserializationContext context) {
        Device device = null;
        switch (numberType) {
            case (1):
                device = new Phone();
                ((Phone) device).setType(context.deserialize(jObj.get("type"), TypePhone.class));
                break;
            case (2):
                device = new Smartphone();
                ((Smartphone) device).setTypeSim(context.deserialize(jObj.get("typeSim"), TypeSIM.class));
                ((Smartphone) device).setNumberSim(jObj.get("numberSim").getAsInt());
                break;
            case (3):
                device = new Tablet();
                ((Tablet) device).setVideoProcessor(jObj.get("videoProcessor").toString().substring(1, jObj.get("videoProcessor").toString().length() - 1));
                ((Tablet) device).setScreenResolution(context.deserialize(jObj.get("screenResolution"), Dimension.class));
                break;
        }
        return device;
    }

    @Override
    public Device deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();

        int numberType = identifyTypeOfDevice(jObject);
        Device device = addSpecific(numberType, jObject, context);

        device.setId(context.deserialize(jObject.get("id"), UUID.class));
        device.setName(jObject.get("name").toString().substring(1, jObject.get("name").toString().length() - 1));
        device.setPrice(jObject.get("price").getAsDouble());
        device.setFirm(jObject.get("firm").toString().substring(1, jObject.get("firm").toString().length() - 1));
        device.setModel(jObject.get("model").toString().substring(1, jObject.get("model").toString().length() - 1));
        device.setOs(jObject.get("os").toString().substring(1, jObject.get("os").toString().length() - 1));

        return device;
    }

}