package com.fdvalls.importadora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private Integer old;
    @Column
    private String identification; //dni
    
}
