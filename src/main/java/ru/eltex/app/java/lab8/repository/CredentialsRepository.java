package ru.eltex.app.java.lab8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.app.java.lab8.model.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {

}