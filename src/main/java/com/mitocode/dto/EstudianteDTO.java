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
public class EstudianteDTO {
    @EqualsAndHashCode.Include
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String nombresEstudiante;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String apellidosEstudiante;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 20)
    private String dniEstudiante;
    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private int edadEstudiante;
}
