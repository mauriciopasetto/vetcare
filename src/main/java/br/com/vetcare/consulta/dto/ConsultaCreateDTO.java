package br.com.vetcare.consulta.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaCreateDTO(
        @NotNull Long animalId,
        @NotNull Long veterinarioId,
        @NotNull @FutureOrPresent LocalDate dataConsulta,
        @NotNull LocalTime horaConsulta,
        String historico
) {}