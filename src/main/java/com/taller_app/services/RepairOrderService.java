package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.repositories.IRepairOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderService implements IRepairOrderService{

    private final IRepairOrderRepository repairOrderRepository;

    public RepairOrderService (IRepairOrderRepository orderRepository) {
        this.repairOrderRepository = orderRepository;
    }


    @Override
    public RepairOrderOutDTO createRepairOrder(RepairOrderInDTO repairOrderInDTO) {
        return null;
    }

    @Override
    public RepairOrderOutDTO findRepairOrderById(Long repairOrderId) {
        return null;
    }

    @Override
    public List<RepairOrderOutDTO> findAllRepairOrders() {
        return null;
    }

    @Override
    public RepairOrderOutDTO updateRepairOrder(Long repairOrderId, RepairOrderInDTO repairOrderInDTO) {
        return null;
    }

    @Override
    public String deleteRepairOrder(Long repairOrderId) {
        return null;
    }
}
