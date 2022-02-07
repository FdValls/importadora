package com.fdvalls.importadora.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "motorcycle")
public class Motorcycle {

    @Id
    private Long id;

    private String chasis;

    private Engine engine;

    @OneToMany
    private List<Wheel> ruedas;

    private String patente;

    private String marca;

    private String year;

    private Long kms;

    private boolean estado;

    private int peso;

}
