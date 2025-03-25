package com.taller_app.dtos.outDTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairOrderOutDTO {

    private Long id;
    private LocalDate date;
    private Map<String, Double> repairs;
    private Double totalCost;

    private Long customerId;
    private String customerName;
    private String customerLastName;
    private String customerPhone;

    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleBrand;
}
