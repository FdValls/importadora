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
import javax.persistence.ManyToOne;
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
@Table(name = "importer")
public class Importer {

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
    @Column(name = "country")
    private String country;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "importer_social_network",
        joinColumns = {@JoinColumn(name = "importer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "social_network_id", referencedColumnName = "id")})
    private List<SocialNetwork> networks;
    @OneToMany(mappedBy = "importer")
    private List<Motorcycle> motorcycles;
    // @ManyToOne
    // @JoinColumn(name = "motorcycle_id")
    // private Motorcycle motorcycle;
    @ManyToMany
    @JoinTable(name = "importer_dealer",
        joinColumns = {@JoinColumn(name = "importer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "dealer_id", referencedColumnName = "id")})
    private List<Dealer> dealers;

}
