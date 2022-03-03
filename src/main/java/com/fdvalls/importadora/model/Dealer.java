package com.fdvalls.importadora.model;

import java.util.List;

import javax.persistence.CascadeType;
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
    @Column(name = "id")
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dealer_social_network", 
        joinColumns = {@JoinColumn(name = "dealer_id", referencedColumnName = "id") },
        inverseJoinColumns = {@JoinColumn(name = "social_network_id", referencedColumnName = "id") })
    private List<SocialNetwork> networks;
    @OneToMany(mappedBy = "dealer")
    private List<Motorcycle> motorcycles;
    @ManyToMany
    @JoinTable(name = "dealer_customer", 
        joinColumns = {@JoinColumn(name = "dealer_id", referencedColumnName = "id") },
        inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id") })
    private List<Customer> customers;

    //Esto lo vi en internet, es lo que te comente que difiere un poco a como lo venia haciendo
    // @ManyToMany(cascade = {
    //         CascadeType.PERSIST,
    //         CascadeType.MERGE
    // })
    // @JoinTable(name = "dealer_customer", joinColumns = { @JoinColumn(name = "dealer_id", referencedColumnName = "id") }, inverseJoinColumns = {
    //         @JoinColumn(name = "customer_id", referencedColumnName = "id") })
    // private Set<Customer> customers;

}
