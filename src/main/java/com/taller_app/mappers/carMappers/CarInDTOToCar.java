package com.taller_app.mappers.carMappers;

import com.taller_app.dtos.inDTOs.CarInDTO;
import com.taller_app.entities.Car;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//  Mapping CarInDTO To Car with ModelMapper

@Component
public class CarInDTOToCar {

    @Autowired
    private ModelMapper modelMapper;

    public Car map(CarInDTO carInDTO) {

        TypeMap<CarInDTO, Car> propertyMapper = modelMapper.getTypeMap(CarInDTO.class, Car.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(CarInDTO.class, Car.class);

        }

        propertyMapper.addMapping(CarInDTO::getNumber, Car::setNumber);
        propertyMapper.addMapping(CarInDTO::getBrand, Car::setBrand);

        Car car = propertyMapper.map(carInDTO);
        return car;
    }
}
