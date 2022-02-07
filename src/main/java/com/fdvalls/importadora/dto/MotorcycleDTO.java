package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorcycleDTO {

    private Long id;
    private String brand; // marca
    private String engineBrand;
    private String enginePower;
    private String domain;

}
