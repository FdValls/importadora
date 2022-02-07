package com.fdvalls.importadora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wheel")
public class Wheel {

    @Id
    @Column
    private Long id;
    @Column
    private String marca;
    @Column
    private Integer rodado;

}
