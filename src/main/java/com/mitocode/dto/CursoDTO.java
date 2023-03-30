package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CursoDTO {
    @EqualsAndHashCode.Include
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
