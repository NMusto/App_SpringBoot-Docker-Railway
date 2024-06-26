package com.taller_app.dtos.outDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOutDTO {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
}
