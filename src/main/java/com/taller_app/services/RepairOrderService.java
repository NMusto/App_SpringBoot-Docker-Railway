package com.taller_app.services;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.entities.Customer;
import com.taller_app.entities.RepairOrder;
import com.taller_app.entities.Vehicle;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.repairOrderMappers.RepairOrderProjectionToRepairOrderOutDTO;
import com.taller_app.mappers.repairOrderMappers.RepairOrderToRepairOrderOutDTO;
import com.taller_app.projections.ICustomerProjection;
import com.taller_app.projections.IRepairOrderProjection;
import com.taller_app.repositories.IRepairOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RepairOrderService implements IRepairOrderService{

    private final IRepairOrderRepository repairOrderRepository;
    private final IVehicleService vehicleService;
    private final ICustomerService customerService;
    private final RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO;
    private final RepairOrderProjectionToRepairOrderOutDTO repairOrderProjectionToRepairOrderOutDTO;

    public RepairOrderService (IRepairOrderRepository orderRepository,
                               IVehicleService vehicleService,
                               ICustomerService customerService,
                               RepairOrderToRepairOrderOutDTO repairOrderToRepairOrderOutDTO, RepairOrderProjectionToRepairOrderOutDTO repairOrderProjectionToRepairOrderOutDTO) {
        this.repairOrderRepository = orderRepository;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.repairOrderToRepairOrderOutDTO = repairOrderToRepairOrderOutDTO;
        this.repairOrderProjectionToRepairOrderOutDTO = repairOrderProjectionToRepairOrderOutDTO;
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

        return repairOrderOutDTO;
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




    /*------------------------------------------------------------------------------------------------*/
    /*                                    REPAIR ORDER: UTIlS                                         */
    /*------------------------------------------------------------------------------------------------*/


    public IRepairOrderProjection findRepairOrderProjection(Long repairOrderId) {
        Optional<IRepairOrderProjection> optionalIRepairOrderProjection = repairOrderRepository.findRepairOrderById(repairOrderId) ;
        if (optionalIRepairOrderProjection.isEmpty()) {
            throw new GeneralException("repairOrderId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalIRepairOrderProjection.get();
    }
}
