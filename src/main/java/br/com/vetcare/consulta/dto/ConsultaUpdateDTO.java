package br.com.vetcare.consulta.dto;

import br.com.vetcare.consulta.model.StatusConsulta;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaUpdateDTO(
        @NotNull LocalDate dataConsulta,
        @NotNull LocalTime horaConsulta,
        @NotNull StatusConsulta status,
        String historico
) {}
