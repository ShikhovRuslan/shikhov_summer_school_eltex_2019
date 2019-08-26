package ru.eltex.app.java.lab5;

import com.google.gson.*;
import ru.eltex.app.java.lab2.Credentials;

import java.util.UUID;
import java.lang.reflect.Type;

public class CredentialsDeserializer implements JsonDeserializer<Credentials> {

    @Override
    public Credentials deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObj = json.getAsJsonObject();
        Credentials user = new Credentials();

        user.setId(context.deserialize(jObj.get("id"), UUID.class));
        user.setSurname(jObj.get("surname").toString().substring(1, jObj.get("surname").toString().length() - 1));
        user.setName(jObj.get("name").toString().substring(1, jObj.get("name").toString().length() - 1));
        user.setPatronymic(jObj.get("patronymic").toString().substring(1, jObj.get("patronymic").toString().length() - 1));
        user.setEmail(jObj.get("email").toString().substring(1, jObj.get("email").toString().length() - 1));

        return user;
    }

}