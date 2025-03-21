package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String dni;
    private String phone;

    @OneToMany(targetEntity = Vehicle.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<RepairOrder> orders = new ArrayList<>();
}
