package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;

import java.util.List;
import java.util.Map;

public interface IRepairOrderService {
    RepairOrderOutDTO createRepairOrder(RepairOrderInDTO repairOrderInDTO);
    RepairOrderOutDTO findRepairOrderById (Long repairOrderId);
    List<RepairOrderOutDTO> findAllRepairOrders();
    String updateRepairOrder (Long repairOrderId, RepairOrderInDTO repairOrderInDTO);
    String deleteRepairOrder (Long repairOrderId);

    String addRepairs (Long repairOrderId, Map<String, Double> repairs);

    String deleteRepair (Long repairOrderId, String key);

    String updateRepairCompleted (Long repairOrderId, boolean value);

    List<RepairOrderOutDTO> findPendingRepairOrders ();

    List<RepairOrderOutDTO> findCompletedRepairOrders ();

}
