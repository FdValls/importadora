package com.fdvalls.importadora.model;

import java.util.List;

import lombok.Data;

@Data
public class Importer {

    private String razonSocial;
    private String cuil;
    private String direccion;
    private String telefono;
    private List<String> redes;
    private List<Motocycle> motos;
    private List<Dealer> clientes;

}
