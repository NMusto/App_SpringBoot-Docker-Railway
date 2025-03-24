package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.ICustomerProjection;
import org.springframework.stereotype.Component;


//  Mapping CustomerProjection to CustomerOutDTO with a custom mapper (IMapper) and Builder Pattern

@Component
public class CustomerProjectionToCustomerOutDTO implements IMapper<ICustomerProjection, CustomerOutDTO> {

    @Override
    public CustomerOutDTO map(ICustomerProjection iCustomerProjection) {

        CustomerOutDTO customerOutDTO = CustomerOutDTO.builder()
                .id(iCustomerProjection.getId())
                .name(iCustomerProjection.getName())
                .lastName(iCustomerProjection.getLastName())
                .dni(iCustomerProjection.getDni())
                .phone(iCustomerProjection.getPhone())
                .build();

        return customerOutDTO;
    }
}
