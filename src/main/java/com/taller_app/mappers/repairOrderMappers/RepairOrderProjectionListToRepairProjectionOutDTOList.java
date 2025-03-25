package com.taller_app.mappers.repairOrderMappers;

import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.projections.IRepairOrderProjection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepairOrderProjectionListToRepairProjectionOutDTOList {

   private final ModelMapper modelMapper;

   public RepairOrderProjectionListToRepairProjectionOutDTOList(ModelMapper modelMapper) {
       this.modelMapper = modelMapper;
   }

   public List<RepairOrderOutDTO> map(List<IRepairOrderProjection> repairOrderProjectionList) {
       return repairOrderProjectionList.stream()
               .map(projection -> modelMapper.map(projection, RepairOrderOutDTO.class))
               .collect(Collectors.toList());
   }
}
