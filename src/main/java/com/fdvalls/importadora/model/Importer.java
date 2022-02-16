package com.fdvalls.importadora.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Importer {

    private Long id;
    private String razonSocial;
    private String cuil;
    private String direccion;
    private String telefono;
    private List<String> redes;
    private List<Motorcycle> motos;
    //private List<Dealer> clientes;

}
