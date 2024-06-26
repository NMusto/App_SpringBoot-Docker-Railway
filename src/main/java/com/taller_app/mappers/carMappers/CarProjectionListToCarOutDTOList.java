package com.taller_app.mappers.carMappers;


import com.taller_app.dtos.outDTOs.CarOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.ICarProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


//  Mapping CarProjectionList To CarOutDTOList with a custom mapper (IMapper)

@Component
public class CarProjectionListToCarOutDTOList implements IMapper< List<ICarProjection>, List<CarOutDTO>> {

    private final CarProjectionToCarOutDTO carProjectionToCarOutDTO;

    public CarProjectionListToCarOutDTOList(CarProjectionToCarOutDTO carProjectionToCarOutDTO) {
        this.carProjectionToCarOutDTO = carProjectionToCarOutDTO;
    }

    @Override
    public List<CarOutDTO> map(List<ICarProjection> iCarProjectionList) {

        List<CarOutDTO> carOutDTOList = iCarProjectionList.stream()
                .map(iCarProjection -> {
                    return carProjectionToCarOutDTO.map(iCarProjection);
                })
                .collect(Collectors.toList());

        return carOutDTOList;
    }
}
