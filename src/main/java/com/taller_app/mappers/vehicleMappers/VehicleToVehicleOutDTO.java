package com.taller_app.mappers.vehicleMappers;

import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.entities.Vehicle;
import com.taller_app.mappers.IMapper;
import org.springframework.stereotype.Component;


//  Mapping Vehicle To VehicleOutDTO with a custom mapper and Builder Pattern

@Component
public class VehicleToVehicleOutDTO implements IMapper<Vehicle, VehicleOutDTO> {

    @Override
    public VehicleOutDTO map(Vehicle vehicle) {

        VehicleOutDTO vehicleOutDTO = VehicleOutDTO.builder()
                .id(vehicle.getId())
                .number(vehicle.getNumber())
                .brand(vehicle.getBrand())
                .customerId( vehicle.getCustomer() != null ? vehicle.getCustomer().getId() : null )
                .customerName( vehicle.getCustomer() != null ? vehicle.getCustomer().getName() : null )
                .customerLastName( vehicle.getCustomer() != null ? vehicle.getCustomer().getLastName() : null )
                .customerDni( vehicle.getCustomer() != null ? vehicle.getCustomer().getDni() : null )
                .customerPhone( vehicle.getCustomer() != null ? vehicle.getCustomer().getPhone() : null )
                .build();

        return vehicleOutDTO;
    }
}
