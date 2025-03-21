package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String brand;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @OneToOne(mappedBy = "vehicle")
    RepairOrder repairOrder;
}

