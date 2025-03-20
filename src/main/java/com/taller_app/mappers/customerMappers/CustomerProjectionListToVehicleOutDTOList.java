package com.taller_app.mappers.customerMappers;

import com.taller_app.dtos.outDTOs.CustomerOutDTO;
import com.taller_app.mappers.IMapper;
import com.taller_app.projections.ICustomerProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


//  Mapping CustomerProjectionList To VehicleOutDTOList with a custom mapper (IMapper)


@Component
public class CustomerProjectionListToVehicleOutDTOList implements IMapper<List<ICustomerProjection>, List<CustomerOutDTO>> {

    private final CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO;

    public CustomerProjectionListToVehicleOutDTOList(CustomerProjectionToCustomerOutDTO customerProjectionToCustomerOutDTO) {
        this.customerProjectionToCustomerOutDTO = customerProjectionToCustomerOutDTO;
    }

    @Override
    public List<CustomerOutDTO> map(List<ICustomerProjection> iCustomerProjectionList) {

        List<CustomerOutDTO> customerOutDTOList = iCustomerProjectionList.stream()
                .map(iCustomerProjection -> {
                    return customerProjectionToCustomerOutDTO.map(iCustomerProjection);
                })
                .collect(Collectors.toList());

        return customerOutDTOList;
    }
}
