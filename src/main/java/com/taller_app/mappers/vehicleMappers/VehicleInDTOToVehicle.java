package com.taller_app.mappers.vehicleMappers;

import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.entities.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//  Mapping VehicleInDTO To Vehicle with ModelMapper

@Component
public class VehicleInDTOToVehicle {

    @Autowired
    private ModelMapper modelMapper;

    public Vehicle map(VehicleInDTO vehicleInDTO) {

        TypeMap<VehicleInDTO, Vehicle> propertyMapper = modelMapper.getTypeMap(VehicleInDTO.class, Vehicle.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(VehicleInDTO.class, Vehicle.class);

        }

        propertyMapper.addMapping(VehicleInDTO::getNumber, Vehicle::setNumber);
        propertyMapper.addMapping(VehicleInDTO::getBrand, Vehicle::setBrand);

        Vehicle vehicle = propertyMapper.map(vehicleInDTO);
        return vehicle;
    }
}
