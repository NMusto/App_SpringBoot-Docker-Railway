package com.taller_app.repositories;

import com.taller_app.entities.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepairOrderRepository extends JpaRepository<RepairOrder, Long> {
}
