package com.taller_app.repositories;

import com.taller_app.entities.RepairOrder;
import com.taller_app.projections.IRepairOrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    Optional<IRepairOrderProjection> findRepairOrderById(Long repairOrderId);

    List<IRepairOrderProjection> findAllProjectedBy();


}
