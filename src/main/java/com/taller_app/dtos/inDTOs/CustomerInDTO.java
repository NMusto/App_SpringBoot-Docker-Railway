package com.taller_app.dtos.inDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInDTO {

    private String name;
    private String lastName;
    private String dni;
    private String phone;
}
