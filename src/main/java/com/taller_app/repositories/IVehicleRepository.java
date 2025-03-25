package com.taller_app.repositories;

import com.taller_app.entities.Vehicle;
import com.taller_app.projections.IVehicleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    List<IVehicleProjection> findAllProjectedBy();

    Optional<IVehicleProjection> findVehicleById(Long vehicleId);
}
