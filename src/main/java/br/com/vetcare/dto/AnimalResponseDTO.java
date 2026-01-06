package br.com.vetcare.dto;

import br.com.vetcare.model.Especie;
import br.com.vetcare.model.Sexo;

import java.time.LocalDate;

public record AnimalResponseDTO (

        Long id,
        String nome,
        String raca,
        Sexo sexo,
        Especie especie,
        LocalDate dataNascimento,
        String rga,
        boolean vivo

)
{}
