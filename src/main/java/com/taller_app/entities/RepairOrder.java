package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean repairCompleted;
    private Double totalCost;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "repairs", joinColumns = @JoinColumn(name = "repair_order_id"))
    @MapKeyColumn(name = "description")
    @Column(name = "cost")
    private Map<String, Double> repairs;
}
