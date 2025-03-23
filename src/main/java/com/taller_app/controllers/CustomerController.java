package com.taller_app.controllers;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.CustomerVehiclesOutDTO;
import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Customer Test!", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerOutDTO> createCustomer(@RequestBody CustomerInDTO customerInDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findcustomer/{customerId}")
    public ResponseEntity<CustomerOutDTO> findCustomerById(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CustomerOutDTO>> findAllCustomers() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerOutDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerInDTO customerInDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerInDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    // Retrieves all vehicles of a customer
    @GetMapping("/findvehicles/{customerId}")
    public ResponseEntity<CustomerVehiclesOutDTO> findVehiclesByCustomerId (@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findCustomerVehicles(customerId), HttpStatus.OK);
    }

}
