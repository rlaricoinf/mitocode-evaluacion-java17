package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    private String nombreCurso;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    private String siglasCurso;
    @NotNull
    private boolean estadoCurso;
}
