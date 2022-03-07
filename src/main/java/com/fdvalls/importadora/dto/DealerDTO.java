package com.fdvalls.importadora.dto;

import java.util.List;

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
    private List<SocialNetworkDTO> socialNetworks;
    private Long importerId;
    
}
