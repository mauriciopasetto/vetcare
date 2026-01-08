package br.com.vetcare.animal.dto;

import br.com.vetcare.animal.model.Especie;
import br.com.vetcare.animal.model.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AnimalCreateDTO (

    @NotBlank @Size(max =80)
    String nome,
    @NotNull
    String raca,
    @NotNull
    Sexo sexo,
    @NotNull
    Especie especie,
    @PastOrPresent
    LocalDate dataNascimento,
    @NotBlank @Size(max = 30)
    String rga,
    @NotNull
    Boolean vivo,
    @NotNull Long tutorId

)
{}
