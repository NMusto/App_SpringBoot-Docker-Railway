package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;

import java.util.List;

public interface IRepairOrderService {
    RepairOrderOutDTO createRepairOrder(RepairOrderInDTO repairOrderInDTO);
    RepairOrderOutDTO findRepairOrderById (Long repairOrderId);
    List<RepairOrderOutDTO> findAllRepairOrders();
    RepairOrderOutDTO updateRepairOrder (Long repairOrderId, RepairOrderInDTO repairOrderInDTO);
    String deleteRepairOrder (Long repairOrderId);

    
}
