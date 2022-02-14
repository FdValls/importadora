package com.fdvalls.importadora.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "wheel")
public class Wheel {

    //Del lado de muchos mapeo la otra entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "rodado")
    private Integer rodado ;
    @ManyToOne
    @JoinColumn(name = "motorcycle_id")
    private Motorcycle motorcycle;

}
