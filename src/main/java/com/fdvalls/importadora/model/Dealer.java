package com.fdvalls.importadora.model;

import java.util.List;

import javax.persistence.Entity;
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

   private Long id;
   private String razonSocial;
   private String cuil;
   private String direccion;
   private String telefono;
   private List<String> redes;
   private List<Motorcycle> motos;
   private List<Customer> clientes;

}
