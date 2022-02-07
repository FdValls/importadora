package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private Long id;
    private String name;
    private String lastname;
    private Integer old;
    private String identification; //dni
}
