package br.com.vetcare.animal.mapper;

import br.com.vetcare.animal.dto.AnimalCreateDTO;
import br.com.vetcare.animal.dto.AnimalPatchDTO;
import br.com.vetcare.animal.dto.AnimalUpdateDTO;
import br.com.vetcare.animal.dto.AnimalResponseDTO;
import br.com.vetcare.animal.model.Animal;

public final class AnimalMapper {

    private AnimalMapper() {}

    public static Animal toEntity(AnimalCreateDTO dto) {
        if (dto == null) return null;

        Animal animal = new Animal();
        animal.setNome(dto.nome());
        animal.setRaca(dto.raca());
        animal.setSexo(dto.sexo());
        animal.setEspecie(dto.especie());
        animal.setDataNascimento(dto.dataNascimento());
        animal.setRga(dto.rga());
        if (dto.vivo() != null) animal.setVivo(dto.vivo());
        animal.setCastrado(dto.castrado());
        animal.setCorPelagem(dto.corPelagem());
        animal.setPorte(dto.porte());
        animal.setPeso(dto.peso());
        animal.setObservacoes(dto.observacoes());

        return animal;
    }

    public static void applyUpdate(AnimalUpdateDTO dto, Animal entity) {
        if (dto == null || entity == null) return;

        entity.setNome(dto.nome());
        entity.setRaca(dto.raca());
        entity.setSexo(dto.sexo());
        entity.setEspecie(dto.especie());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setRga(dto.rga());
        entity.setVivo(Boolean.TRUE.equals(dto.vivo()));
        entity.setCastrado(dto.castrado());
        entity.setCorPelagem(dto.corPelagem());
        entity.setPorte(dto.porte());
        entity.setPeso(dto.peso());
        entity.setObservacoes(dto.observacoes());

    }

    /**
     * PATCH: aplica somente os campos não nulos.
     * Observação: RGA é tratado no Service (validação de duplicidade).
     */
    public static void applyPatch(AnimalPatchDTO dto, Animal entity) {
        if (dto == null || entity == null) return;

        if (dto.nome() != null) entity.setNome(dto.nome());
        if (dto.raca() != null) entity.setRaca(dto.raca());
        if (dto.sexo() != null) entity.setSexo(dto.sexo());
        if (dto.rga() != null) entity.setRga(dto.rga());
        if (dto.especie() != null) entity.setEspecie(dto.especie());
        if (dto.dataNascimento() != null) entity.setDataNascimento(dto.dataNascimento());
        if (dto.vivo() != null) entity.setVivo(dto.vivo());
        if (dto.castrado() != null) entity.setCastrado(dto.castrado());
        if (dto.corPelagem() != null) entity.setCorPelagem(dto.corPelagem());
        if (dto.porte() != null) entity.setPorte(dto.porte());
        if (dto.peso() != null) entity.setPeso(dto.peso());
        if (dto.observacoes() != null) entity.setObservacoes(dto.observacoes());
    }

    public static AnimalResponseDTO toResponseDTO(Animal entity) {
        if (entity == null) return null;

        return new AnimalResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getRaca(),
                entity.getSexo(),
                entity.getEspecie(),
                entity.getDataNascimento(),
                entity.getRga(),
                entity.getVivo(),
                entity.getDataCriacao(),
                entity.getCastrado(),
                entity.getCorPelagem(),
                entity.getPorte(),
                entity.getPeso(),
                entity.getObservacoes(),
                entity.getTutor().getId()
        );
    }
}

