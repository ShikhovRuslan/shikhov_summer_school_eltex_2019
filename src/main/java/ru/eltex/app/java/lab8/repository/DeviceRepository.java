package ru.eltex.app.java.lab8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.app.java.lab8.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

}