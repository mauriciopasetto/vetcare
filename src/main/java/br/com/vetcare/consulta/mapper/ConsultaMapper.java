package br.com.vetcare.consulta.mapper;

import br.com.vetcare.consulta.dto.ConsultaCreateDTO;
import br.com.vetcare.consulta.dto.ConsultaPatchDTO;
import br.com.vetcare.consulta.dto.ConsultaResponseDTO;
import br.com.vetcare.consulta.dto.ConsultaUpdateDTO;
import br.com.vetcare.consulta.model.Consulta;
import br.com.vetcare.consulta.model.StatusConsulta;


public final class ConsultaMapper {
    public static Consulta toEntity(ConsultaCreateDTO dto) {
        if (dto == null) return null;
        return Consulta.builder()
                .dataConsulta(dto.dataConsulta())
                .horaConsulta(dto.horaConsulta())
                .historico(dto.historico())
                .status(StatusConsulta.MARCADA) // Valor padrão inicial
                .build();
    }

    public static ConsultaResponseDTO toResponseDTO(Consulta entity) {
        return new ConsultaResponseDTO(
                entity.getId(),
                entity.getAnimal().getNome(),
                entity.getVeterinario().getNome(),
                entity.getDataConsulta(),
                entity.getHoraConsulta(),
                entity.getStatus(),
                entity.getHistorico(),
                entity.getDataMarcacao()
        );
    }

    // Adicionar ao ConsultaMapper.java existente
    public static void applyUpdate(ConsultaUpdateDTO dto, Consulta entity) {
        entity.setDataConsulta(dto.dataConsulta());
        entity.setHoraConsulta(dto.horaConsulta());
        entity.setStatus(dto.status());
        entity.setHistorico(dto.historico());
    }

    public static void applyPatch(ConsultaPatchDTO dto, Consulta entity) {
        if (dto.dataConsulta() != null) entity.setDataConsulta(dto.dataConsulta());
        if (dto.horaConsulta() != null) entity.setHoraConsulta(dto.horaConsulta());
        if (dto.status() != null) entity.setStatus(dto.status());
        if (dto.historico() != null) entity.setHistorico(dto.historico());
    }
}
