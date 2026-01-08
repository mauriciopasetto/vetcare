package br.com.vetcare.tutor.dto;


import java.time.LocalDate;

public record TutorPatchDTO(

        String nome,
        LocalDate dataNascimento,
        String cpf,
        String rg,
        String email,
        String telefone,
        TutorEnderecoDTO endereco

) {

}
