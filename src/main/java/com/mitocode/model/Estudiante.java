/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author rlari
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estudiante {

    @EqualsAndHashCode.Include
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="SQ_ESTUDIANTE_MITOCODE", sequenceName="SQ_ESTUDIANTE_MITOCODE",initialValue=1,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_ESTUDIANTE_MITOCODE")
    private Integer id;
    private String nombres;
    private String apellidos;
    private String dni;
    private int edad;
}
