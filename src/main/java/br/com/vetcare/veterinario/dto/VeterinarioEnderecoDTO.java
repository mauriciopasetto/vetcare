package br.com.vetcare.veterinario.dto;

import jakarta.validation.constraints.NotBlank;

public record VeterinarioEnderecoDTO(
        @NotBlank String cep,
        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String uf
) {}

