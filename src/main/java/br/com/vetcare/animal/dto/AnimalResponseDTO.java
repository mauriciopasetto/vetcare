package br.com.vetcare.animal.dto;

import br.com.vetcare.animal.model.Especie;
import br.com.vetcare.animal.model.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimalResponseDTO (

        Long id,
        String nome,
        String raca,
        Sexo sexo,
        Especie especie,
        LocalDate dataNascimento,
        String rga,
        Boolean vivo,
        LocalDateTime dataCriacao,
        Long tutorId

)
{}
