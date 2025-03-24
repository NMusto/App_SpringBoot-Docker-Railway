package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.entities.RepairOrder;
import com.taller_app.entities.Vehicle;
import com.taller_app.mappers.repairOrderMappers.RepairOrderToRepairOrderOutDTO;
import com.taller_app.repositories.IRepairOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RepairOrderService implements IRepairOrderService{

    private final IRepairOrderRepository repairOrderRepository;
    private final IVehicleService vehicleService;
    private final ICustomerService customerService;
    private final RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO;

    public RepairOrderService (IRepairOrderRepository orderRepository,
                               IVehicleService vehicleService,
                               ICustomerService customerService,
                               RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO) {
        this.repairOrderRepository = orderRepository;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.repairOrderToRepairOrderOutDTO = repairOrderToRepairOrderOutDTO;
    }


    @Override
    public RepairOrderOutDTO createRepairOrder(RepairOrderInDTO repairOrderInDTO) {

        Customer customer = customerService.findCustomer(repairOrderInDTO.getCustomerId());
        Vehicle vehicle = vehicleService.findVehicle(repairOrderInDTO.getVehicleId());

        RepairOrder repairOrder = RepairOrder.builder()
                .customer(customer)
                .vehicle(vehicle)
                .date(LocalDate.now())
                .repairCompleted(false)
                .build();

        repairOrder = repairOrderRepository.save(repairOrder);

        RepairOrderOutDTO repairOrderOutDTO = repairOrderToRepairOrderOutDTO.map(repairOrder);
        return repairOrderOutDTO;
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
