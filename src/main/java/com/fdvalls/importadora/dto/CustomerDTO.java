package com.fdvalls.importadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    private String name;
    private String lastname;
    private Integer age;
    private String identification; //dni
    private Long dealerId;
}
