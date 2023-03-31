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
public class Curso {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="SQ_CURSO_MITOCODE", sequenceName="SQ_CURSO_MITOCODE",initialValue=1,allocationSize=1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_CURSO_MITOCODE")
    private Integer id;
    private String nombre;
    private String siglas;
    private boolean estado;
}
