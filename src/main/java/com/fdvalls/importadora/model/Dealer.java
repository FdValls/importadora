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
/*
@Entity
@Table(name = "dealer")
*/
public class Dealer {

   private Long id;
   private String razonSocial;
   private String cuil;
   private String direccion;
   private String telefono;
   private List<String> networks;
   private List<Motorcycle> motorcycles;
   private List<Customer> customers;

}
