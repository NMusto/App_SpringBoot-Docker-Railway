package com.taller_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
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

    @OneToMany(targetEntity = Car.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Car> cars;
}
