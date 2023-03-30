package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mitocode.model.Curso;
import com.mitocode.model.DetalleMatricula;
import com.mitocode.model.Matricula;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleMatriculaDTO {

    @JsonBackReference
    private MatriculaDTO matricula;
    private CursoDTO curso;
    private String aula;

}
