package ru.eltex.app.java.lab8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.app.java.lab8.model.Tablet;

import java.util.UUID;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, UUID> {

}