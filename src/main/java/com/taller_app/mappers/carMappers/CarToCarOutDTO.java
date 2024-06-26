package com.taller_app.mappers.carMappers;

import com.taller_app.dtos.outDTOs.CarOutDTO;
import com.taller_app.entities.Car;
import com.taller_app.mappers.IMapper;
import org.springframework.stereotype.Component;


//  Mapping Car To CarOutDTO with a custom mapper and Builder Pattern

@Component
public class CarToCarOutDTO implements IMapper<Car, CarOutDTO> {

    @Override
    public CarOutDTO map(Car car) {

        CarOutDTO carOutDTO = CarOutDTO.builder()
                .id(car.getId())
                .number(car.getNumber())
                .brand(car.getBrand())
                .customerId( car.getCustomer() != null ? car.getCustomer().getId() : null )
                .customerName( car.getCustomer() != null ? car.getCustomer().getName() : null )
                .customerLastName( car.getCustomer() != null ? car.getCustomer().getLastName() : null )
                .customerDni( car.getCustomer() != null ? car.getCustomer().getDni() : null )
                .customerPhone( car.getCustomer() != null ? car.getCustomer().getPhone() : null )
                .build();

        return carOutDTO;
    }
}
