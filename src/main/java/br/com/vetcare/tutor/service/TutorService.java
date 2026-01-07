package br.com.vetcare.tutor.service;

import br.com.vetcare.tutor.dto.TutorCreateDTO;
import br.com.vetcare.tutor.dto.TutorPatchDTO;
import br.com.vetcare.tutor.dto.TutorResponseDTO;
import br.com.vetcare.tutor.dto.TutorUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface TutorService {

    //Create - Cadastrar um novo tutor
    TutorResponseDTO salvar(TutorCreateDTO dto);

    //Read - buscar tutor por ID
    TutorResponseDTO buscarPorId(Long id);

    //Read - listar tutores
    Page<TutorResponseDTO> listar(Pageable pageable);

    //Update - atulização parcial de tutor
    TutorResponseDTO atualizarParcial(Long id, TutorPatchDTO dto);

    //Update - atualização total
    TutorResponseDTO atualizar(Long id, TutorUpdateDTO dto);

    //Delete - deletar tutor por ID
    void remover(Long id);

}
