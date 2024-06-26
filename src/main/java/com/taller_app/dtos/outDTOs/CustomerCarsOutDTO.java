package com.taller_app.dtos.outDTOs;

import com.taller_app.projections.ICar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCarsOutDTO {

    private Long id;
    private String name;
    private String lastName;
    private String phone;

    private List<ICar> cars;
}
