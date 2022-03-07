package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WheelDTO {
    
    private Long id;
    private String brand;
    private String frontDiameter ;
    private String backDiameter;

}
