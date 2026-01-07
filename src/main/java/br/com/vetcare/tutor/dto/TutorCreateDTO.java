package br.com.vetcare.tutor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record TutorCreateDTO(

        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @Valid
        EnderecoDTO endereco


) {
}
