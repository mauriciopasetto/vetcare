package br.com.vetcare.tutor.dto;


import jakarta.validation.Valid;

public record TutorResponseDTO(

        Long id,
        String nome,
        String cpf,
        String rg,
        String email,
        String telefone,
        EnderecoDTO endereco

) {


}
