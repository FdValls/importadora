package com.fdvalls.importadora.model;

import java.util.List;

import lombok.Data;

@Data
public class Motocycle {

    private String chasis;
    private Engine engine;
    private List<Wheel> ruedas;
    private String patente;
    private String marca;
    private String year;
    private Long kms;
    private boolean estado;
    private int peso;
    
}
