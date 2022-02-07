package com.fdvalls.importadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Engine {

    private Long id;
    private String brand;
    private String nroSerie;
    private String horsePower;

}
