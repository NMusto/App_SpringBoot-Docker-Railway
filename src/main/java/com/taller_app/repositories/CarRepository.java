package com.taller_app.repositories;

import com.taller_app.entities.Car;
import com.taller_app.projections.ICarProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    public List<ICarProjection> findAllProjectedBy();

    public Optional<ICarProjection> findCarById(Long carId);
}
