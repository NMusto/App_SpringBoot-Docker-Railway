package com.taller_app.mappers.vehicleMappers;


import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.IVehicleProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


//  Mapping VehicleProjectionList To VehicleOutDTOList with a custom mapper (IMapper)

@Component
public class VehicleProjectionListToVehicleOutDTOList implements IMapper< List<IVehicleProjection>, List<VehicleOutDTO>> {

    private final VehicleProjectionToVehicleOutDTO vehicleProjectionToVehicleOutDTO;

    public VehicleProjectionListToVehicleOutDTOList(VehicleProjectionToVehicleOutDTO vehicleProjectionToVehicleOutDTO) {
        this.vehicleProjectionToVehicleOutDTO = vehicleProjectionToVehicleOutDTO;
    }

    @Override
    public List<VehicleOutDTO> map(List<IVehicleProjection> iVehicleProjectionList) {

        List<VehicleOutDTO> vehicleOutDTOList = iVehicleProjectionList.stream()
                .map(iVehicleProjection -> {
                    return vehicleProjectionToVehicleOutDTO.map(iVehicleProjection);
                })
                .collect(Collectors.toList());

        return vehicleOutDTOList;
    }
}
