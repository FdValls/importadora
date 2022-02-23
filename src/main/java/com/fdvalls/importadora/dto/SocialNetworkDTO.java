package com.fdvalls.importadora.dto;

import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.Importer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialNetworkDTO {

    private Long id;
    private String description;
    private String url;
    private Dealer dealer;
    private Importer importer;
    
}