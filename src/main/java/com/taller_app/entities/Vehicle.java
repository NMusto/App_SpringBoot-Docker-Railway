package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String brand;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    RepairOrder repairOrder;
}

