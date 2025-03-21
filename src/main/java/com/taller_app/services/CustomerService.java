package com.taller_app.services;

import com.taller_app.dtos.inDTOs.CustomerInDTO;
import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.CustomerVehiclesOutDTO;
import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.entities.Vehicle;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.customerMappers.CustomerInDTOToCustomer;
import com.taller_app.mappers.customerMappers.CustomerProjectionListToVehicleOutDTOList;
import com.taller_app.mappers.customerMappers.CustomerProjectionToCustomerOutDTO;
import com.taller_app.mappers.customerMappers.CustomerToCustomerOutDTO;
import com.taller_app.projections.IVehicle;
import com.taller_app.projections.ICustomerProjection;
import com.taller_app.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerInDTOToCustomer customerInDTOToCustomer;
    private final CustomerToCustomerOutDTO customerToCustomerOutDTO;
    public final CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO;
    private final CustomerProjectionListToVehicleOutDTOList customerProjectionListToVehicleOutDTOList;
    private final IVehicleService vehicleService;

    public CustomerService(CustomerRepository customerRepository, CustomerInDTOToCustomer customerInDTOToCustomer,
                           CustomerToCustomerOutDTO customerToCustomerOutDTO,
                           CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO,
                           CustomerProjectionListToVehicleOutDTOList customerProjectionListToVehicleOutDTOList, ICustomerService customerService, IVehicleService vehicleService) {
        this.customerRepository = customerRepository;
        this.customerInDTOToCustomer = customerInDTOToCustomer;
        this.customerToCustomerOutDTO = customerToCustomerOutDTO;
        this.customerProjectionToCustomerOutDTO = customerProjectionToCustomerOutDTO;
        this.customerProjectionListToVehicleOutDTOList = customerProjectionListToVehicleOutDTOList;
        this.vehicleService = vehicleService;
    }


    @Override
    public CustomerOutDTO createCustomer(CustomerInDTO customerInDTO) {
        Customer customer = customerInDTOToCustomer.map(customerInDTO);
        customerRepository.save(customer);
        CustomerOutDTO customerOutDTO = customerToCustomerOutDTO.map(customer);
        return customerOutDTO;
    }

    @Override
    public CustomerOutDTO findCustomerById (Long customerId) {
        ICustomerProjection iCustomerProjection = this.findCustomerProjection(customerId);
        CustomerOutDTO customerOutDTO = customerProjectionToCustomerOutDTO.map(iCustomerProjection);
        return customerOutDTO;
    }

    @Override
    public List<CustomerOutDTO> findAllCustomers() {
        List<ICustomerProjection> customerProjectionList = customerRepository.findAllProjectedBy();
        List<CustomerOutDTO> customerOutDTOList = customerProjectionListToVehicleOutDTOList.map(customerProjectionList);
        return customerOutDTOList;
    }

    @Override
    public CustomerOutDTO updateCustomer (Long customerId, CustomerInDTO customerInDTO) {
        Customer customer = this.findCustomer(customerId);

        customer.setDni(customerInDTO.getDni());
        customer.setName(customerInDTO.getName());
        customer.setLastName(customerInDTO.getLastName());
        customer.setPhone(customerInDTO.getPhone());

        customerRepository.save(customer);
        return customerToCustomerOutDTO.map(customer);
    }

    @Override
    @Transactional
    public String deleteCustomer(Long customerId) {
        this.findCustomer(customerId);

        customerRepository.deleteCustomerInVehicles(customerId);
        customerRepository.deleteById(customerId);
        return "Customer id: " + customerId + " was successfully deleted!";
    }


    // Retrieves all vehicles of a customer
    @Override
    public CustomerVehiclesOutDTO findCustomerVehicles (Long customerId) {
        Customer customer = this.findCustomer(customerId);
        CustomerVehiclesOutDTO customerVehiclesOutDTO = new CustomerVehiclesOutDTO();

        customerVehiclesOutDTO.setId(customer.getId());
        customerVehiclesOutDTO.setName(customer.getName());
        customerVehiclesOutDTO.setLastName(customer.getLastName());
        customerVehiclesOutDTO.setPhone(customer.getPhone());

        List<IVehicle> vehicles = customerRepository.findVehiclesByCustomerId(customerId);
        if(vehicles.isEmpty()) {
            throw new GeneralException("There are no vehicles for Customer id: " + customerId, HttpStatus.NOT_FOUND);
        }
        customerVehiclesOutDTO.setVehicles(vehicles);

        return customerVehiclesOutDTO;
    }

    // Adds a vehicle to a customer
    @Override
    @Transactional
    public String addVehicleToCustomer(Long customerId, VehicleInDTO vehicleInDTO) {

        Customer customer = this.findCustomer(customerId);

        Vehicle vehicle = vehicleService.create(vehicleInDTO);

        vehicleService.addCustomerToVehicle(vehicle.getId(), customerId);

        return "Vehicle successfully created!";
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
