package com.taller_app.controllers;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.dtos.outDTOs.CustomerCarsOutDTO;
import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
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

    @GetMapping("/findcars/{customerId}")
    public ResponseEntity<CustomerCarsOutDTO> findCarsByCustomerId(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findCustomerCars(customerId), HttpStatus.OK);
    }
}
