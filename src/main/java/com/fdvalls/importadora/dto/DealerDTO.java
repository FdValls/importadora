package com.fdvalls.importadora.dto;

import java.util.List;

import com.fdvalls.importadora.model.Customer;
import com.fdvalls.importadora.model.Motorcycle;
import com.fdvalls.importadora.model.SocialNetwork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealerDTO {

    private Long id;
    private String razonSocial;
    private String cuil;
    private String address;
    private String telephone;
    private List<SocialNetwork> networks;
    private List<Motorcycle> motorcycles;
    private List<Customer> customers;
    
}
