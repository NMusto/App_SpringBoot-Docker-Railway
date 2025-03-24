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

    // asignar una reparación a la orden, detalle y costo
    // eliminar una reparación
    // cambiar el repairCompleted de la orden para cuando el auto pase de en reparacion a terminado o viceversa
    // mostrar las ordenes de acuerdo a el estado , si están finalizados o no
}
