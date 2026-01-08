package br.com.vetcare.tutor.dto;


import br.com.vetcare.animal.dto.AnimalResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TutorResponseDTO(

        Long id,
        String nome,
        String cpf,
        String rg,
        String email,
        String telefone,
        TutorEnderecoDTO endereco,
        LocalDateTime dataCriacao,
        List<AnimalResponseDTO> animais

) {}
