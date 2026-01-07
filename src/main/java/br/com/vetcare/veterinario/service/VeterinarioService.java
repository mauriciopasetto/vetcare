package br.com.vetcare.veterinario.service;

import br.com.vetcare.veterinario.dto.VeterinarioCreateDTO;
import br.com.vetcare.veterinario.dto.VeterinarioPatchDTO;
import br.com.vetcare.veterinario.dto.VeterinarioResponseDTO;
import br.com.vetcare.veterinario.dto.VeterinarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeterinarioService {

    //Create - Cadastrar um novo Veterinario
    VeterinarioResponseDTO salvar(VeterinarioCreateDTO dto);

    //Read - buscar Veterinario por ID
    VeterinarioResponseDTO buscarPorId(Long id);

    //Read - listar Veterinarios
    Page<VeterinarioResponseDTO> listar(Pageable pageable);

    //Update - atulização parcial de Veterinario
    VeterinarioResponseDTO atualizarParcial(Long id, VeterinarioPatchDTO dto);

    //Update - atualização total
    VeterinarioResponseDTO atualizar(Long id, VeterinarioUpdateDTO dto);

    //Delete - deletar Veterinario por ID
    void remover(Long id);
}

