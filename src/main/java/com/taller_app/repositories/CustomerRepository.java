package com.taller_app.repositories;

import com.taller_app.entities.Customer;
import com.taller_app.projections.ICar;
import com.taller_app.projections.ICustomerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<ICustomerProjection> findAllProjectedBy();

    public Optional<ICustomerProjection> findCustomerById(Long customerId);


    @Modifying
    @Query(value = "UPDATE car " +
            "SET customer_id = null " +
            "WHERE customer_id = :customerId", nativeQuery = true)
    public void deleteCustomerInCars(@Param("customerId") Long customerId);

    @Query(value = "SELECT c.id, c.number, c.brand " +
            "FROM car c " +
            "WHERE c.customer_id = :customerId", nativeQuery = true)
    public List<ICar> findCarsByCustomerId(Long customerId);
}
