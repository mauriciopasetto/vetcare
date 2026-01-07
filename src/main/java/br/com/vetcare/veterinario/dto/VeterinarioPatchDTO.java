package br.com.vetcare.veterinario.dto;

import br.com.vetcare.veterinario.model.Especialidade;

public record VeterinarioPatchDTO(
        String nome,
        String cpf,
        String crmv,
        String email,
        String telefone,
        Especialidade especialidade,
        VeterinarioEnderecoDTO endereco
) {}

