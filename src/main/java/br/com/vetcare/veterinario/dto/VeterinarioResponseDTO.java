package br.com.vetcare.veterinario.dto;

import br.com.vetcare.veterinario.model.Especialidade;

import java.time.LocalDateTime;

public record VeterinarioResponseDTO(
        Long id,
        String nome,
        String cpf,
        String crmv,
        String email,
        String telefone,
        Especialidade especialidade,
        VeterinarioEnderecoDTO endereco,
        LocalDateTime dataCriacao
) {}

