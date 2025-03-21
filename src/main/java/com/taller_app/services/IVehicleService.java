package com.taller_app.services;

import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.entities.Vehicle;

import java.util.List;

public interface IVehicleService {
    VehicleOutDTO createVehicle(VehicleInDTO vehicleInDTO);
    Vehicle create(VehicleInDTO vehicleInDTO);
    VehicleOutDTO findVehicleById (Long vehicleId);
    List<VehicleOutDTO> findAllVehicles();
    VehicleOutDTO updateVehicle (Long vehicleId, VehicleInDTO vehicleInDTO);
    String deleteVehicle (Long vehicleId);
    String addCustomerToVehicle (Long vehicleId, Long customerId);
    String deleteCustomerByVehicleId(Long vehicleId);
}
