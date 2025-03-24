package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


//  Mapping CustomerInDTO To Customer with ModelMapper


@Component
public class CustomerInDTOToCustomer {

    private ModelMapper modelMapper;

    public CustomerInDTOToCustomer (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer map(CustomerInDTO customerInDTO) {
        return modelMapper.map(customerInDTO, Customer.class);
    }
}
