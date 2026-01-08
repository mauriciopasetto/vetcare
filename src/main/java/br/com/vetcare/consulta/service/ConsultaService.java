package br.com.vetcare.consulta.service;

import br.com.vetcare.consulta.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultaService {

    ConsultaResponseDTO agendar(ConsultaCreateDTO dto);

    ConsultaResponseDTO buscarPorId(Long id);

    Page<ConsultaResponseDTO> listarTodos(Pageable pageable);

    ConsultaResponseDTO atualizar(Long id, ConsultaUpdateDTO dto);

    ConsultaResponseDTO atualizarParcial(Long id, ConsultaPatchDTO dto);

    void excluir(Long id);
}
