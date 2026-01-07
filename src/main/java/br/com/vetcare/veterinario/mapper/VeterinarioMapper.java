package br.com.vetcare.veterinario.mapper;

import br.com.vetcare.tutor.dto.*;
import br.com.vetcare.veterinario.dto.*;
import br.com.vetcare.veterinario.model.EnderecoVeterinario;
import br.com.vetcare.veterinario.model.Veterinario;

public final class VeterinarioMapper {

    private VeterinarioMapper() {}


    public static Veterinario toEntity(VeterinarioCreateDTO dto){
        if (dto == null) return null;

        Veterinario veterinario = new Veterinario();
        veterinario.setNome(dto.nome());
        veterinario.setCpf(dto.cpf());
        veterinario.setCrmv(dto.crmv());
        veterinario.setEmail(dto.email());
        veterinario.setTelefone(dto.telefone());
        veterinario.setEspecialidade(dto.especialidade());

        if (dto.endereco() != null) {
            veterinario.setEnderecoVeterinario(toEnderecoEntity(dto.endereco()));
        }
        return veterinario;
    }


    public static VeterinarioResponseDTO toResponseDTO(Veterinario entity) {
        if (entity == null) return null;
        return new VeterinarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getCrmv(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getEspecialidade(),
                toEnderecoDTO(entity.getEnderecoVeterinario()),
                entity.getDataCriacao()
        );
    }


    public static void applyUpdate(VeterinarioUpdateDTO dto, Veterinario entity){
        if (dto == null || entity == null) return;
        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setCrmv(dto.crmv());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());
        entity.setEspecialidade(dto.especialidade());
        toEnderecoDTO(entity.getEnderecoVeterinario());
        if (dto.endereco() != null) {
            entity.setEnderecoVeterinario(toEnderecoEntity(dto.endereco()));
        }
    }


    public static void applyPatch(VeterinarioPatchDTO dto, Veterinario entity) {
        if (dto == null || entity == null) return;

        if (dto.nome() != null) entity.setNome(dto.nome());
        if (dto.cpf() != null) entity.setCpf(dto.cpf());
        if (dto.crmv() != null) entity.setCrmv(dto.crmv());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.telefone() != null) entity.setTelefone(dto.telefone());
        if (dto.especialidade() != null) entity.setEspecialidade(dto.especialidade());

        if (dto.endereco() != null) {
            if (entity.getEnderecoVeterinario() == null) entity.setEnderecoVeterinario(new EnderecoVeterinario());

            VeterinarioEnderecoDTO eDto = dto.endereco();
            EnderecoVeterinario eEnt = entity.getEnderecoVeterinario();

            if (eDto.cep() != null) eEnt.setCep(eDto.cep());
            if (eDto.logradouro() != null) eEnt.setLogradouro(eDto.logradouro());
            if (eDto.numero() != null) eEnt.setNumero(eDto.numero());
            if (eDto.complemento() != null) eEnt.setComplemento(eDto.complemento());
            if (eDto.cidade() != null) eEnt.setCidade(eDto.cidade());
            if (eDto.estado() != null) eEnt.setEstado(eDto.estado());
            if (eDto.uf() != null) eEnt.setUf(eDto.uf());
        }
    }


    private static EnderecoVeterinario toEnderecoEntity(VeterinarioEnderecoDTO dto) {
        return new EnderecoVeterinario(dto.cep(), dto.logradouro(), dto.numero(),
                dto.complemento(), dto.cidade(), dto.estado(), dto.uf());
    }

    private static VeterinarioEnderecoDTO toEnderecoDTO(EnderecoVeterinario entity) {
        if (entity == null) return null;
        return new VeterinarioEnderecoDTO(entity.getCep(), entity.getLogradouro(), entity.getNumero(),
                entity.getComplemento(), entity.getCidade(), entity.getEstado(), entity.getUf());
    }
}
