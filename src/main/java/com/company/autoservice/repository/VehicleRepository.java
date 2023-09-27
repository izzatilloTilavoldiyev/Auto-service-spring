package com.company.autoservice.repository;

import com.company.autoservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByVIN(String VIN);
}
