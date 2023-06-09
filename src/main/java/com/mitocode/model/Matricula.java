/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author rlari
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Matricula {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="SQ_MATRICULA_MITOCODE", sequenceName="SQ_MATRICULA_MITOCODE",initialValue=1,allocationSize=1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_MATRICULA_MITOCODE")
    private Integer id;
    private LocalDateTime fechaMatricula;
    private boolean estado;
    @JoinColumn(name = "id_estudiante", nullable = false)
    @ManyToOne
    private Estudiante estudiante;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<DetalleMatricula> detalle;

}
