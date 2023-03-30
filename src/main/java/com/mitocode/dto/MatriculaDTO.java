package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.DetalleMatricula;
import com.mitocode.model.Estudiante;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO {

    //@Min(value = 1)
    private Integer id;
    private LocalDateTime fechaMatricula;
    private boolean estado;

    @NotNull
    private EstudianteDTO estudiante;

    @NotNull
    @JsonManagedReference
    private List<DetalleMatriculaDTO> detalle;

}
