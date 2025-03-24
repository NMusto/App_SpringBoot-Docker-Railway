package com.taller_app.projections;

import java.time.LocalDate;
import java.util.Map;

public interface IRepairOrderProjection {

    Long getId();
    LocalDate getDate();
    Map<String, Double> getRepairs();

    Long getCustomerId();
    String getCustomerName();
    String getCustomerLastName();
    String getCustomerPhone();

    Long getVehicleId();
    String getVehicleNumber();
    String getVehicleBrand();
}
