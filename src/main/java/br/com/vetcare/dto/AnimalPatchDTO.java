package br.com.vetcare.dto;

import br.com.vetcare.model.Especie;
import br.com.vetcare.model.Sexo;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AnimalPatchDTO(

        @Size(max = 80) String nome,
        @Size(max = 80) String raca,
        Sexo sexo,
        Especie especie,
        @PastOrPresent LocalDate dataNascimento,
        @Size(max = 30) String rga,
        Boolean vivo

) {}
