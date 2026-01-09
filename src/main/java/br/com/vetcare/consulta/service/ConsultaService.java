package br.com.vetcare.consulta.service;

import br.com.vetcare.consulta.dto.ConsultaCreateDTO;
import br.com.vetcare.consulta.dto.ConsultaPatchDTO;
import br.com.vetcare.consulta.dto.ConsultaResponseDTO;
import br.com.vetcare.consulta.dto.ConsultaUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ConsultaService {

    ConsultaResponseDTO agendar(ConsultaCreateDTO dto);

    ConsultaResponseDTO buscarPorId(Long id);

    Page<ConsultaResponseDTO> listarTodos(Pageable pageable);

    ConsultaResponseDTO atualizar(Long id, ConsultaUpdateDTO dto);

    ConsultaResponseDTO atualizarParcial(Long id, ConsultaPatchDTO dto);

    void excluir(Long id);

    Page<ConsultaResponseDTO> listarPorAnimal(Long animalId, Pageable pageable);

    Page<ConsultaResponseDTO> listarPorVeterinario(Long veterinarioId, Pageable pageable);

    Page<ConsultaResponseDTO> listarApartirDe(LocalDate data, Pageable pageable);

    Page<ConsultaResponseDTO> listarPorData(LocalDate data, Pageable pageable);

    Page<ConsultaResponseDTO> listarPorPeriodo(LocalDate inicio, LocalDate fim, Pageable pageable);
}
