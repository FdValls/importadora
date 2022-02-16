package com.fdvalls.importadora.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "dealer")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "cuil")
    private String cuil;
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "dealer")
    private List<Network> networks;
    @OneToMany(mappedBy = "dealer")
    private List<Motorcycle> motorcycles;
    /*
    //
    private List<Customer> customers;*/

}