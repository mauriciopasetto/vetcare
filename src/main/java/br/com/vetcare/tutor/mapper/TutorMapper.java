package br.com.vetcare.tutor.mapper;

import br.com.vetcare.tutor.dto.*;
import br.com.vetcare.tutor.model.Endereco;
import br.com.vetcare.tutor.model.Tutor;

public final class TutorMapper {

    private TutorMapper() {}


    public static Tutor toEntity(TutorCreateDTO dto){
        if (dto == null) return null;

        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setCpf(dto.cpf());
        tutor.setRg(dto.rg());
        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());
        if (dto.endereco() != null) {
            tutor.setEndereco(toEnderecoEntity(dto.endereco()));
        }

        return tutor;
    }


    public static TutorResponseDTO toResponseDTO(Tutor entity) {
        if (entity == null) return null;

        return new TutorResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getRg(),
                entity.getEmail(),
                entity.getTelefone(),
                toEnderecoDTO(entity.getEndereco())
        );
    }


    public static void applyUpdate(TutorUpdateDTO dto, Tutor entity){
        if (dto == null || entity == null) return;

        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setRg(dto.rg());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());
        toEnderecoDTO(entity.getEndereco());


    }


    public static void applyPatch(TutorPatchDTO dto, Tutor entity) {
        if (dto == null || entity == null) return;

        if (dto.nome() != null) entity.setNome(dto.nome());
        if (dto.cpf() != null) entity.setCpf(dto.cpf());
        if (dto.rg() != null) entity.setRg(dto.rg());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.telefone() != null) entity.setTelefone(dto.telefone());

        if (dto.endereco() != null) {
            if (entity.getEndereco() == null) entity.setEndereco(new Endereco());

            EnderecoDTO eDto = dto.endereco();
            Endereco eEnt = entity.getEndereco();

            if (eDto.cep() != null) eEnt.setCep(eDto.cep());
            if (eDto.logradouro() != null) eEnt.setLogradouro(eDto.logradouro());
            if (eDto.numero() != null) eEnt.setNumero(eDto.numero());
            if (eDto.complemento() != null) eEnt.setComplemento(eDto.complemento());
            if (eDto.cidade() != null) eEnt.setCidade(eDto.cidade());
            if (eDto.estado() != null) eEnt.setEstado(eDto.estado());
            if (eDto.uf() != null) eEnt.setUf(eDto.uf());
        }

    }


    private static Endereco toEnderecoEntity(EnderecoDTO dto) {
        return new Endereco(dto.cep(), dto.logradouro(), dto.numero(),
                dto.complemento(), dto.cidade(), dto.estado(), dto.uf());
    }

    private static EnderecoDTO toEnderecoDTO(Endereco entity) {
        if (entity == null) return null;
        return new EnderecoDTO(entity.getCep(), entity.getLogradouro(), entity.getNumero(),
                entity.getComplemento(), entity.getCidade(), entity.getEstado(), entity.getUf());
    }

}
