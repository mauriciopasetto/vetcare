package br.com.vetcare.service;

import br.com.vetcare.dto.AnimalCreateDTO;
import br.com.vetcare.dto.AnimalPatchDTO;
import br.com.vetcare.dto.AnimalResponseDTO;

import br.com.vetcare.dto.AnimalUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface AnimalService {

    //Create - Cadastrar um novo animal
    AnimalResponseDTO salvar(AnimalCreateDTO dto);

    //Read - buscar animal por ID
    AnimalResponseDTO buscarPorId(Long id);

    //Read - listar animais
    Page<AnimalResponseDTO> listar(Pageable pageable);

    //Update - atulização parcial de animal
    AnimalResponseDTO atualizarParcial(Long id, AnimalPatchDTO dto);

    //Update - atualização total
    AnimalResponseDTO atualizar(Long id, AnimalUpdateDTO dto);

    //Delete - deletar animal por ID
    void remover(Long id);

}
