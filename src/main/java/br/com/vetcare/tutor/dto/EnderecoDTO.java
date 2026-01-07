package br.com.vetcare.tutor.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank String cep,
        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String uf
) {}
