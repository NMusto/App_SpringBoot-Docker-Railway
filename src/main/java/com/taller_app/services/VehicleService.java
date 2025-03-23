package com.taller_app.services;

import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.entities.Vehicle;
import com.taller_app.entities.Customer;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.vehicleMappers.VehicleInDTOToVehicle;
import com.taller_app.mappers.vehicleMappers.VehicleProjectionListToVehicleOutDTOList;
import com.taller_app.mappers.vehicleMappers.VehicleProjectionToVehicleOutDTO;
import com.taller_app.mappers.vehicleMappers.VehicleToVehicleOutDTO;
import com.taller_app.projections.IVehicleProjection;
import com.taller_app.repositories.IVehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements IVehicleService{

    private final IVehicleRepository IVehicleRepository;
    private final VehicleInDTOToVehicle vehicleInDTOToVehicle;
    private final CustomerService customerService;
    private final VehicleProjectionToVehicleOutDTO vehicleProjectionToVehicleOutDTO;
    private final VehicleToVehicleOutDTO vehicleToVehicleOutDTO;
    private final VehicleProjectionListToVehicleOutDTOList vehicleProjectionListToVehicleOutDTOList;

    public VehicleService(IVehicleRepository IVehicleRepository,
                          VehicleInDTOToVehicle vehicleInDTOToVehicle,
                          CustomerService customerService,
                          VehicleProjectionToVehicleOutDTO vehicleProjectionToVehicleOutDTO,
                          VehicleToVehicleOutDTO vehicleToVehicleOutDTO,
                          VehicleProjectionListToVehicleOutDTOList vehicleProjectionListToVehicleOutDTOList) {
        this.IVehicleRepository = IVehicleRepository;
        this.vehicleInDTOToVehicle = vehicleInDTOToVehicle;
        this.customerService = customerService;
        this.vehicleProjectionToVehicleOutDTO = vehicleProjectionToVehicleOutDTO;
        this.vehicleToVehicleOutDTO = vehicleToVehicleOutDTO;
        this.vehicleProjectionListToVehicleOutDTOList = vehicleProjectionListToVehicleOutDTOList;
    }


    @Override
    public VehicleOutDTO createVehicle (VehicleInDTO vehicleInDTO) {
        Vehicle vehicle = vehicleInDTOToVehicle.map(vehicleInDTO);
        IVehicleRepository.save(vehicle);
        VehicleOutDTO vehicleOutDTO = vehicleToVehicleOutDTO.map(vehicle);
        return vehicleOutDTO;
    }

    @Override
    public Vehicle create (VehicleInDTO vehicleInDTO) {
        Vehicle vehicle = vehicleInDTOToVehicle.map(vehicleInDTO);
        return IVehicleRepository.save(vehicle);
    }

    @Override
    public VehicleOutDTO findVehicleById (Long vehicleId) {
        IVehicleProjection iVehicleProjection = this.findVehicleProjection(vehicleId);
        VehicleOutDTO vehicleOutDTO = vehicleProjectionToVehicleOutDTO.map(iVehicleProjection);
        return vehicleOutDTO;
    }

    @Override
    public List<VehicleOutDTO> findAllVehicles() {
        List<IVehicleProjection> vehicleProjectionList = IVehicleRepository.findAllProjectedBy();
        List<VehicleOutDTO> vehicleOutDTOList = vehicleProjectionListToVehicleOutDTOList.map(vehicleProjectionList);
        return vehicleOutDTOList;
    }

    @Override
    public VehicleOutDTO updateVehicle (Long vehicleId, VehicleInDTO vehicleInDTO) {
        Vehicle vehicle = this.findVehicle(vehicleId);

        vehicle.setNumber(vehicleInDTO.getNumber());
        vehicle.setBrand(vehicleInDTO.getBrand());

        IVehicleRepository.save(vehicle);
        return vehicleToVehicleOutDTO.map(vehicle);
    }

    @Override
    public String deleteVehicle (Long vehicleId) {
        Vehicle vehicle = this.findVehicle(vehicleId);

        IVehicleRepository.delete(vehicle);
        return "Vehicle id: " + vehicleId + " was successfully deleted!";
    }

    @Override
    public String addCustomerToVehicle (Long vehicleId, Long customerId) {
        Vehicle vehicle = this.findVehicle(vehicleId);
        Customer customer = customerService.findCustomer(customerId);

        vehicle.setCustomer(customer);
        IVehicleRepository.save(vehicle);
        return "Customer id " + customerId + " was successfully added to Vehicle id " + vehicleId;
    }

    @Override
    public String deleteCustomerByVehicleId(Long vehicleId) {
        Vehicle vehicle = this.findVehicle(vehicleId);

        vehicle.setCustomer(null);
        IVehicleRepository.save(vehicle);
        return "Customer was successfully deleted from Vehicle id " + vehicleId;
    }




    /*------------------------------------------------------------------------------------------------*/
    /*                                    VEHICLE: UTIlS                                                  */
    /*------------------------------------------------------------------------------------------------*/


    public Vehicle findVehicle (Long vehicleId) {
        Optional<Vehicle> optionalVehicle = IVehicleRepository.findById(vehicleId);
        if(optionalVehicle.isEmpty()) {
            throw new GeneralException("VehicleId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalVehicle.get();
    }

    public IVehicleProjection findVehicleProjection (Long vehicleId) {
        Optional<IVehicleProjection> optionalVehicleProjection = IVehicleRepository.findVehicleById(vehicleId);
        if(optionalVehicleProjection.isEmpty()) {
            throw new GeneralException("VehicleId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalVehicleProjection.get();
    }

}
