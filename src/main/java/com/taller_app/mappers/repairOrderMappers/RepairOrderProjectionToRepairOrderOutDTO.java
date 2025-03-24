package com.taller_app.mappers.repairOrderMappers;

import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.projections.IRepairOrderProjection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Mapping RepairOrder projection to RepairOrderOutDTO with Model Mapper

@Component
public class RepairOrderProjectionToRepairOrderOutDTO {

    private ModelMapper modelMapper;

    public RepairOrderProjectionToRepairOrderOutDTO (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RepairOrderOutDTO map(IRepairOrderProjection repairOrderProjection) {
        return modelMapper.map(repairOrderProjection, RepairOrderOutDTO.class);
    }
}
