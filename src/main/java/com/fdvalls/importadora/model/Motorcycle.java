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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "chasis")
    private String chasis;
    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @OneToMany(mappedBy = "motorcycle")
    private List<Wheel> wheels;
    @Column(name = "domain")
    private String domain;
    @Column(name = "brand")
    private String brand;
    @Column(name = "year")
    private int year;
    @Column(name = "kms")
    private int kms;
    @Column(name = "is_new")
    private boolean isNew;
    @Column(name = "weigth")
    private int weigth;
    @ManyToMany
    @JoinTable(name = "motorcycle_customer",
    joinColumns = {@JoinColumn(name = "motorcycle_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")})
    private List<Customer> customers;
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;
    @ManyToOne
    @JoinColumn(name = "importer_id")
    private Importer importer;
   
}
