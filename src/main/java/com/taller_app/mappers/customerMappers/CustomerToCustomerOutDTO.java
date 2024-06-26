package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.mappers.IMapper;
import org.springframework.stereotype.Component;


//  Mapping Customer To CustomerOutDTO with a custom mapper and Builder Pattern

@Component
public class CustomerToCustomerOutDTO implements IMapper<Customer, CustomerOutDTO> {

    @Override
    public CustomerOutDTO map(Customer customer) {

        CustomerOutDTO customerOutDTO = CustomerOutDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .phone(customer.getPhone())
                .build();

        return customerOutDTO;
    }
}
