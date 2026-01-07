package br.com.vetcare.veterinario.dto;

import br.com.vetcare.veterinario.model.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeterinarioCreateDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String crmv,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @Valid
        VeterinarioEnderecoDTO endereco
) {}

