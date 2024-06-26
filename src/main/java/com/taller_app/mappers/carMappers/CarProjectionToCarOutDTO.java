package com.taller_app.mappers.carMappers;

import com.taller_app.dtos.outDTOs.CarOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.ICarProjection;
import org.springframework.stereotype.Component;


//  Mapping CarProjection to CarOutDTO with a custom mapper (IMapper)

@Component
public class CarProjectionToCarOutDTO  implements IMapper<ICarProjection, CarOutDTO>{

    @Override
    public CarOutDTO map(ICarProjection iCarProjection) {

        CarOutDTO carOutDTO = new CarOutDTO();

        carOutDTO.setId(iCarProjection.getId());
        carOutDTO.setNumber(iCarProjection.getNumber());
        carOutDTO.setBrand(iCarProjection.getBrand());
        carOutDTO.setCustomerId(iCarProjection.getCustomerId());
        carOutDTO.setCustomerName(iCarProjection.getCustomerName());
        carOutDTO.setCustomerLastName(iCarProjection.getCustomerLastName());
        carOutDTO.setCustomerDni(iCarProjection.getCustomerDni());
        carOutDTO.setCustomerPhone(iCarProjection.getCustomerPhone());

        return carOutDTO;
    }
}
