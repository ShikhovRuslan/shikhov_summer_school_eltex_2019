package ru.eltex.app.java.lab8.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

import static ru.eltex.app.java.lab1.Device.getRandom;

@Entity
@Table(name = "credentials")
public class Credentials implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @NotNull
    private String email;

    public Credentials() {

    }

    public Credentials(UUID id) {
        this.id = id;
    }

    public Credentials(UUID id, String surname, String name, String patronymic, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void create() {
        surname = getRandom(new String[]{"surname1", "surname2", "surname3"});
        name = getRandom(new String[]{"name1", "name2", "name3"});
        patronymic = getRandom(new String[]{"patronymic1", "patronymic2", "patronymic3"});
        email = getRandom(new String[]{"email1", "email2", "email3"});
    }

}