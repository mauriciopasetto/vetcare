package br.com.vetcare.tutor.dto;


public record TutorPatchDTO(

        String nome,
        String cpf,
        String rg,
        String email,
        String telefone,
        TutorEnderecoDTO endereco

) {

}
