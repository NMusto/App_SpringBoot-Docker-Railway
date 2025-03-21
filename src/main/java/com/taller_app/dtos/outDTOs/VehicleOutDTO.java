package com.taller_app.dtos.outDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleOutDTO {

    private Long id;
    private String number;
    private String brand;

    private Long customerId;
    private String customerName;
    private String customerLastName;
    private String customerDni;
    private String customerPhone;

    private Long repairOrderId;
}
