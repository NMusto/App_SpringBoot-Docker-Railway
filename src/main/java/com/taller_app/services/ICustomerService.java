package com.taller_app.services;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.dtos.outDTOs.CustomerVehiclesOutDTO;

import java.util.List;

public interface ICustomerService {
    CustomerOutDTO createCustomer(CustomerInDTO customerInDTO);
    CustomerOutDTO findCustomerById (Long customerId);
    List<CustomerOutDTO> findAllCustomers();
    CustomerOutDTO updateCustomer (Long customerId, CustomerInDTO customerInDTO);
    String deleteCustomer(Long customerId);
    CustomerVehiclesOutDTO findCustomerVehicles (Long customerId);
    String addVehicleToCustomer(Long customerId, VehicleInDTO vehicleInDTO);
}
