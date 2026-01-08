package br.com.vetcare.consulta.dto;

import br.com.vetcare.consulta.model.StatusConsulta;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaPatchDTO(
        LocalDate dataConsulta,
        LocalTime horaConsulta,
        StatusConsulta status,
        String historico
) {}