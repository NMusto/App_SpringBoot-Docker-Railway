package com.taller_app.projections;

public interface IVehicleProjection {

    Long getId();
    String getNumber();
    String getBrand();

    Long getCustomerId();
    String getCustomerName();
    String getCustomerLastName();
    String getCustomerDni();
    String getCustomerPhone();
}
