package br.com.vetcare.tutor.dto;


import java.time.LocalDateTime;

public record TutorResponseDTO(

        Long id,
        String nome,
        String cpf,
        String rg,
        String email,
        String telefone,
        TutorEnderecoDTO endereco,
        LocalDateTime dataCriacao

) {


}
