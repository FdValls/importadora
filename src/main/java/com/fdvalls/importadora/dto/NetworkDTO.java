package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkDTO {

    private Long id;
    private String description;
    private String url;
    //private Dealer dealer;
    //private Importer importer;
    
}
