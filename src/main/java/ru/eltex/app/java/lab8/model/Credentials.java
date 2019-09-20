package ru.eltex.app.java.lab8.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "credentials")
public class Credentials implements Serializable {

    @Id
    private String id;

    @NotBlank
    private String surname;

    @NotBlank
    private String name;

    @NotBlank
    private String patronymic;

    @NotBlank
    private String email;

    public Credentials() {
    }

    public Credentials(String surname, String name, String patronymic, String email) {
        id = UUID.randomUUID().toString();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}