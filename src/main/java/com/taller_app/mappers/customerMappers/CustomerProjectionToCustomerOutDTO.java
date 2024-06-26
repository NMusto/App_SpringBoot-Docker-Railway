package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.ICustomerProjection;
import org.springframework.stereotype.Component;


//  Mapping CustomerProjection to CustomerOutDTO with a custom mapper (IMapper)

@Component
public class CustomerProjectionToCustomerOutDTO implements IMapper<ICustomerProjection, CustomerOutDTO> {

    @Override
    public CustomerOutDTO map(ICustomerProjection iCustomerProjection) {

        CustomerOutDTO customerOutDTO = new CustomerOutDTO();

        customerOutDTO.setId(iCustomerProjection.getId());
        customerOutDTO.setName(iCustomerProjection.getName());
        customerOutDTO.setLastName(iCustomerProjection.getLastName());
        customerOutDTO.setDni(iCustomerProjection.getDni());
        customerOutDTO.setPhone(iCustomerProjection.getPhone());

        return customerOutDTO;
    }
}
