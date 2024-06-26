package com.taller_app.services;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.dtos.outDTOs.CustomerCarsOutDTO;
import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.customerMappers.CustomerInDTOToCustomer;
import com.taller_app.mappers.customerMappers.CustomerProjectionListToCarOutDTOList;
import com.taller_app.mappers.customerMappers.CustomerProjectionToCustomerOutDTO;
import com.taller_app.mappers.customerMappers.CustomerToCustomerOutDTO;
import com.taller_app.projections.ICar;
import com.taller_app.projections.ICustomerProjection;
import com.taller_app.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerInDTOToCustomer customerInDTOToCustomer;
    private final CustomerToCustomerOutDTO customerToCustomerOutDTO;
    public final CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO;
    private final CustomerProjectionListToCarOutDTOList customerProjectionListToCarOutDTOList;

    public CustomerService(CustomerRepository customerRepository, CustomerInDTOToCustomer customerInDTOToCustomer,
                           CustomerToCustomerOutDTO customerToCustomerOutDTO,
                           CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO,
                           CustomerProjectionListToCarOutDTOList customerProjectionListToCarOutDTOList) {
        this.customerRepository = customerRepository;
        this.customerInDTOToCustomer = customerInDTOToCustomer;
        this.customerToCustomerOutDTO = customerToCustomerOutDTO;
        this.customerProjectionToCustomerOutDTO = customerProjectionToCustomerOutDTO;
        this.customerProjectionListToCarOutDTOList = customerProjectionListToCarOutDTOList;
    }


    public CustomerOutDTO createCustomer(CustomerInDTO customerInDTO) {
        Customer customer = customerInDTOToCustomer.map(customerInDTO);
        customerRepository.save(customer);
        CustomerOutDTO customerOutDTO = customerToCustomerOutDTO.map(customer);
        return customerOutDTO;
    }

    public CustomerOutDTO findCustomerById (Long customerId) {
        ICustomerProjection iCustomerProjection = this.findCustomerProjection(customerId);
        CustomerOutDTO customerOutDTO = customerProjectionToCustomerOutDTO.map(iCustomerProjection);
        return customerOutDTO;
    }

    public List<CustomerOutDTO> findAllCustomers() {
        List<ICustomerProjection> customerProjectionList = customerRepository.findAllProjectedBy();
        List<CustomerOutDTO> customerOutDTOList = customerProjectionListToCarOutDTOList.map(customerProjectionList);
        return customerOutDTOList;
    }

    public CustomerOutDTO updateCustomer (Long customerId, CustomerInDTO customerInDTO) {
        Customer customer = this.findCustomer(customerId);

        customer.setDni(customerInDTO.getDni());
        customer.setName(customerInDTO.getName());
        customer.setLastName(customerInDTO.getLastName());
        customer.setPhone(customerInDTO.getPhone());

        customerRepository.save(customer);
        return customerToCustomerOutDTO.map(customer);
    }

    @Transactional
    public String deleteCustomer(Long customerId) {
        this.findCustomer(customerId);

        customerRepository.deleteCustomerInCars(customerId);
        customerRepository.deleteById(customerId);
        return "Customer id: " + customerId + " was successfully deleted!";
    }

    public CustomerCarsOutDTO findCustomerCars(Long customerId) {
        Customer customer = this.findCustomer(customerId);
        CustomerCarsOutDTO customerCarsOutDTO = new CustomerCarsOutDTO();

        customerCarsOutDTO.setId(customer.getId());
        customerCarsOutDTO.setName(customer.getName());
        customerCarsOutDTO.setLastName(customer.getLastName());
        customerCarsOutDTO.setPhone(customer.getPhone());

        List<ICar> cars = customerRepository.findCarsByCustomerId(customerId);
        if(cars.isEmpty()) {
            throw new GeneralException("There are no cars for Customer id: " + customerId, HttpStatus.NOT_FOUND);
        }
        customerCarsOutDTO.setCars(cars);

        return customerCarsOutDTO;
    }


    /*------------------------------------------------------------------------------------------------*/
    /*                                    CUSTOMER: UTIlS                                                  */
    /*------------------------------------------------------------------------------------------------*/


    public Customer findCustomer (Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) {
            throw new GeneralException("CustomerId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCustomer.get();
    }

    public ICustomerProjection findCustomerProjection (Long customerId) {
        Optional<ICustomerProjection> optionalCustomerProjection = customerRepository.findCustomerById(customerId);
        if(optionalCustomerProjection.isEmpty()) {
            throw new GeneralException("CustomerId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCustomerProjection.get();
    }
}
