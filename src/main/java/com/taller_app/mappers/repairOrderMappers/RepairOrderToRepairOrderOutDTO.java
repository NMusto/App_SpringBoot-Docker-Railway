package com.taller_app.mappers.repairOrderMappers;

import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.entities.RepairOrder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RepairOrderToRepairOrderOutDTO {

    private final ModelMapper modelMapper;

    public RepairOrderToRepairOrderOutDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(RepairOrder.class, RepairOrderOutDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getCustomer().getId(), RepairOrderOutDTO::setCustomerId);
                    mapper.map(src -> src.getCustomer().getName(), RepairOrderOutDTO::setCustomerName);
                    mapper.map(src -> src.getCustomer().getLastName(), RepairOrderOutDTO::setCustomerLastName);
                    mapper.map(src -> src.getCustomer().getPhone(), RepairOrderOutDTO::setCustomerPhone);

                    mapper.map(src -> src.getVehicle().getId(), RepairOrderOutDTO::setVehicleId);
                    mapper.map(src -> src.getVehicle().getNumber(), RepairOrderOutDTO::setVehicleNumber);
                    mapper.map(src -> src.getVehicle().getBrand(), RepairOrderOutDTO::setVehicleBrand);
                });
    }

    public RepairOrderOutDTO map(RepairOrder repairOrder) {
        return modelMapper.map(repairOrder, RepairOrderOutDTO.class);
    }
}
