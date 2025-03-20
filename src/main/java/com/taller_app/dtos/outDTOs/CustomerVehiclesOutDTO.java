package com.taller_app.dtos.outDTOs;

import com.taller_app.projections.IVehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehiclesOutDTO {

    private Long id;
    private String name;
    private String lastName;
    private String phone;

    private List<IVehicle> vehicles;
}
