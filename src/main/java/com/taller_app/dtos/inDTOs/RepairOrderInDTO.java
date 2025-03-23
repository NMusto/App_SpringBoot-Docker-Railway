package com.taller_app.dtos.inDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairOrderInDTO {
    private Long customerId;
    private Long vehicleId;
}
