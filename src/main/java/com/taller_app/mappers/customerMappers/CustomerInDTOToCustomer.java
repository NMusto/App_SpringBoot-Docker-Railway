package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.entities.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//  Mapping CustomerInDTO To Customer with ModelMapper


@Component
public class CustomerInDTOToCustomer {

    @Autowired
    private ModelMapper modelMapper;

    public Customer map(CustomerInDTO customerInDTO) {

        TypeMap<CustomerInDTO, Customer> propertyMapper = modelMapper.getTypeMap(CustomerInDTO.class, Customer.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(CustomerInDTO.class, Customer.class);

        }
        propertyMapper.addMapping(CustomerInDTO::getName, Customer::setName);
        propertyMapper.addMapping(CustomerInDTO::getLastName, Customer::setLastName);
        propertyMapper.addMapping(CustomerInDTO::getDni, Customer::setDni);
        propertyMapper.addMapping(CustomerInDTO::getPhone, Customer::setPhone);

        Customer customer = propertyMapper.map(customerInDTO);
        return customer;
    }
}
