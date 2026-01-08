package br.com.vetcare.consulta.dto;

import br.com.vetcare.consulta.model.StatusConsulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ConsultaResponseDTO(
        Long id,
        String nomeAnimal,
        String nomeVeterinario,
        LocalDate dataConsulta,
        LocalTime horaConsulta,
        StatusConsulta status,
        String historico,
        LocalDateTime dataMarcacao
) {}
