package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


//  Mapping Customer to CustomerOutDTO with ModelMapper

@Component
public class CustomerToCustomerOutDTO {

   private ModelMapper modelMapper;

   public CustomerToCustomerOutDTO(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
   }

    public CustomerOutDTO map(Customer customer) {
       return modelMapper.map(customer, CustomerOutDTO.class);
    }
}
