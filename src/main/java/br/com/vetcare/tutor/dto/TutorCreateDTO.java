package br.com.vetcare.tutor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TutorCreateDTO(

        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @Valid
        TutorEnderecoDTO endereco


) {
}
