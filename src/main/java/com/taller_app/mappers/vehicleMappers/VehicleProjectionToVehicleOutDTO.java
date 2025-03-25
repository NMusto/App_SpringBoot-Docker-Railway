package com.taller_app.mappers.vehicleMappers;

import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.IRepairOrderProjection;
import com.taller_app.projections.IVehicleProjection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


//  Mapping VehicleProjection to VehicleOutDTO with Model Mapper

@Component
public class VehicleProjectionToVehicleOutDTO implements IMapper<IVehicleProjection, VehicleOutDTO>{

    private ModelMapper modelMapper;

    public VehicleProjectionToVehicleOutDTO (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VehicleOutDTO map(IVehicleProjection vehicleProjection) {
        return modelMapper.map(vehicleProjection, VehicleOutDTO.class);
    }
}
