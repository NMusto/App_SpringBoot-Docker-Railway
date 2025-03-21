package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "repair_items", joinColumns = @JoinColumn(name = "repair_order_id"))
    @MapKeyColumn(name = "description")
    @Column(name = "cost")
    private Map<String, Double> repairs;
}
