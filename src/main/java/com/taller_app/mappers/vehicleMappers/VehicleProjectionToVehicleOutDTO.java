package com.taller_app.mappers.vehicleMappers;

import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.IVehicleProjection;
import org.springframework.stereotype.Component;


//  Mapping VehicleProjection to VehicleOutDTO with a custom mapper (IMapper)

@Component
public class VehicleProjectionToVehicleOutDTO implements IMapper<IVehicleProjection, VehicleOutDTO>{

    @Override
    public VehicleOutDTO map(IVehicleProjection iVehicleProjection) {

        VehicleOutDTO vehicleOutDTO = new VehicleOutDTO();

        vehicleOutDTO.setId(iVehicleProjection.getId());
        vehicleOutDTO.setNumber(iVehicleProjection.getNumber());
        vehicleOutDTO.setBrand(iVehicleProjection.getBrand());
        vehicleOutDTO.setCustomerId(iVehicleProjection.getCustomerId());
        vehicleOutDTO.setCustomerName(iVehicleProjection.getCustomerName());
        vehicleOutDTO.setCustomerLastName(iVehicleProjection.getCustomerLastName());
        vehicleOutDTO.setCustomerDni(iVehicleProjection.getCustomerDni());
        vehicleOutDTO.setCustomerPhone(iVehicleProjection.getCustomerPhone());
        vehicleOutDTO.setRepairOrderId(iVehicleProjection.getRepairOrderId());

        return vehicleOutDTO;
    }
}
