package com.taller_app.mappers.vehicleMappers;

import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.entities.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


//  Mapping VehicleInDTO To Vehicle with ModelMapper

@Component
public class VehicleInDTOToVehicle {

    private ModelMapper modelMapper;

    public VehicleInDTOToVehicle (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(VehicleInDTO.class, Vehicle.class)
                .addMappings(mapper -> {
                   mapper.map(VehicleInDTO::getNumber, Vehicle::setNumber);
                   mapper.map(VehicleInDTO::getBrand, Vehicle::setBrand);
                });
    }

    public Vehicle map(VehicleInDTO vehicleInDTO) {
        return modelMapper.map(vehicleInDTO, Vehicle.class);
    }
}
