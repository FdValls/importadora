package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MotorcycleDTO {

    private Long id;
    private String brand; 
    private String chasis;
    private String domain;
    private boolean isNew;
    private int kms;
    private int weight;
    private int year;

    private Long dealerId;
    private Long engineId;
    private Long importerId;
    private Long wheelId;

    public boolean getIsNew(){
        return this.isNew;
    }

}
