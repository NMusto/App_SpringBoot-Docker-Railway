package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.entities.RepairOrder;
import com.taller_app.entities.Vehicle;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.repairOrderMappers.RepairOrderProjectionListToRepairProjectionOutDTOList;
import com.taller_app.mappers.repairOrderMappers.RepairOrderProjectionToRepairOrderOutDTO;
import com.taller_app.mappers.repairOrderMappers.RepairOrderToRepairOrderOutDTO;
import com.taller_app.projections.ICustomerProjection;
import com.taller_app.projections.IRepairOrderProjection;
import com.taller_app.repositories.IRepairOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RepairOrderService implements IRepairOrderService{

    private final IRepairOrderRepository repairOrderRepository;
    private final IVehicleService vehicleService;
    private final ICustomerService customerService;
    private final RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO;
    private final RepairOrderProjectionToRepairOrderOutDTO repairOrderProjectionToRepairOrderOutDTO;
    private final RepairOrderProjectionListToRepairProjectionOutDTOList repairOrderProjectionListToRepairProjectionOutDTOList;

    public RepairOrderService (IRepairOrderRepository orderRepository,
                               IVehicleService vehicleService,
                               ICustomerService customerService,
                               RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO,
                               RepairOrderProjectionToRepairOrderOutDTO repairOrderProjectionToRepairOrderOutDTO, RepairOrderProjectionListToRepairProjectionOutDTOList repairOrderProjectionListToRepairProjectionOutDTOList) {
        this.repairOrderRepository = orderRepository;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.repairOrderToRepairOrderOutDTO = repairOrderToRepairOrderOutDTO;
        this.repairOrderProjectionToRepairOrderOutDTO = repairOrderProjectionToRepairOrderOutDTO;
        this.repairOrderProjectionListToRepairProjectionOutDTOList = repairOrderProjectionListToRepairProjectionOutDTOList;
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
        IRepairOrderProjection iRepairOrderProjection = this.findRepairOrderProjection(repairOrderId);
        RepairOrderOutDTO repairOrderOutDTO = repairOrderProjectionToRepairOrderOutDTO.map(iRepairOrderProjection);

        Double totalCost = this.totalCostRepairs(repairOrderOutDTO.getRepairs());
        repairOrderOutDTO.setTotalCost(totalCost);

        return repairOrderOutDTO;
    }

    @Override
    public List<RepairOrderOutDTO> findAllRepairOrders() {
        List<IRepairOrderProjection> repairOrderProjectionList = repairOrderRepository.findAllProjectedBy();
        List<RepairOrderOutDTO> repairOrderOutDTOList = repairOrderProjectionListToRepairProjectionOutDTOList.map(repairOrderProjectionList);

        return repairOrderOutDTOList;
    }

    @Override
    public String updateRepairOrder(Long repairOrderId, RepairOrderInDTO repairOrderInDTO) {

        RepairOrder repairOrder = this.findRepairOrder(repairOrderId);
        Customer customer = customerService.findCustomer(repairOrderInDTO.getCustomerId());
        Vehicle vehicle = vehicleService.findVehicle(repairOrderInDTO.getVehicleId());

        repairOrder.setCustomer(customer);
        repairOrder.setVehicle(vehicle);

        repairOrderRepository.save(repairOrder);

        return "RepairOrder id: " + repairOrderId + " was successfully updated!";
    }

    @Override
    public String deleteRepairOrder(Long repairOrderId) {
        RepairOrder repairOrder = this.findRepairOrder(repairOrderId);
        repairOrderRepository.deleteById(repairOrderId);
        return "RepairOrder id: " + repairOrderId + " was successfully deleted!";
    }

    @Override
    public String addRepairs (Long repairOrderId, Map<String, Double> newRepairs) {
        RepairOrder repairOrder = this.findRepairOrder(repairOrderId);

        if ( newRepairs != null && !newRepairs.isEmpty()) {
            repairOrder.getRepairs().putAll(newRepairs);
        }

        repairOrderRepository.save(repairOrder);
        return "Repairs successfully added to the order id " + repairOrderId;
    }

    @Override
    public String deleteRepair (Long repairOrderId, String key) {
        RepairOrder repairOrder = this.findRepairOrder(repairOrderId);

        if (repairOrder.getRepairs().containsKey(key)) {
            repairOrder.getRepairs().remove(key);
        }

        repairOrderRepository.save(repairOrder);
        return "Repair successfully removed from order id " + repairOrderId;
    }

    @Override
    public String updateRepairCompleted (Long repairOrderId, boolean value) {
        RepairOrder repairOrder = this.findRepairOrder(repairOrderId);
        repairOrder.setRepairCompleted(value);
        repairOrderRepository.save(repairOrder);
        return "RepairOrder status successfully updated in order id " + repairOrderId;
    }

    @Override
    public List<RepairOrderOutDTO> findPendingRepairOrders () {
        List<IRepairOrderProjection> repairOrderProjectionList = repairOrderRepository.findRepairOrdersByRepairCompleted(false);
        List<RepairOrderOutDTO> repairOrderOutDTOList = repairOrderProjectionListToRepairProjectionOutDTOList.map(repairOrderProjectionList);
        return repairOrderOutDTOList;
    }

    @Override
    public List<RepairOrderOutDTO> findCompletedRepairOrders () {
        List<IRepairOrderProjection> repairOrderProjectionList = repairOrderRepository.findRepairOrdersByRepairCompleted(true);
        List<RepairOrderOutDTO> repairOrderOutDTOList = repairOrderProjectionListToRepairProjectionOutDTOList.map(repairOrderProjectionList);
        return repairOrderOutDTOList;
    }




    /*------------------------------------------------------------------------------------------------*/
    /*                                    REPAIR ORDER: UTIlS                                         */
    /*------------------------------------------------------------------------------------------------*/


    public RepairOrder findRepairOrder (Long repairOrderId) {
        return repairOrderRepository.findById(repairOrderId)
                .orElseThrow(() -> new GeneralException("RepairOrderId does not exist.", HttpStatus.NOT_FOUND));
    }

    public IRepairOrderProjection findRepairOrderProjection(Long repairOrderId) {
        return repairOrderRepository.findRepairOrderById(repairOrderId)
                .orElseThrow(() -> new GeneralException("repairOrderId does not exist.", HttpStatus.NOT_FOUND));
    }

    public Double totalCostRepairs (Map<String, Double> repairs) {
        Double totalCost = repairs.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return totalCost;
    }
}
