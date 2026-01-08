package br.com.vetcare.animal.dto;

import br.com.vetcare.animal.model.Especie;
import br.com.vetcare.animal.model.Porte;
import br.com.vetcare.animal.model.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AnimalUpdateDTO(

        @NotBlank @Size(max = 80) String nome,
        @NotBlank @Size(max = 80) String raca,
        @NotNull Sexo sexo,
        @NotNull Especie especie,
        @PastOrPresent LocalDate dataNascimento,
        @NotBlank @Size(max = 30) String rga,
        @NotNull Boolean vivo,
        @NotNull Boolean castrado,
        @NotBlank String corPelagem,
        @NotNull Float peso,
        @NotNull Porte porte,
        @NotNull String observacoes

) {}
