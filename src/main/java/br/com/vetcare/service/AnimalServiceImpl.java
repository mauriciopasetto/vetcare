package br.com.vetcare.service;

import br.com.vetcare.dto.AnimalCreateDTO;
import br.com.vetcare.dto.AnimalPatchDTO;
import br.com.vetcare.dto.AnimalResponseDTO;
import br.com.vetcare.dto.AnimalUpdateDTO;
import br.com.vetcare.mapper.AnimalMapper;
import br.com.vetcare.model.Animal;
import br.com.vetcare.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {


    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Override
    @Transactional
    public AnimalResponseDTO salvar(AnimalCreateDTO dto) {
        //cria animal pela conversão do DTO em ENTITY
        Animal animal = AnimalMapper.toEntity(dto);
        //padrão para vivo é true
        if (dto.vivo() == null){
            animal.setVivo(true);
        }
        //verifica se já não existe animal com este RGA
        //caso exista lança uma exceção
        validarRgaDuplicadoNoCadastro(animal.getRga());
        Animal salvo = animalRepository.save(animal);
        return AnimalMapper.toResponseDTO(salvo);
    }

    @Override
    public AnimalResponseDTO buscarPorId(Long id) {
        return AnimalMapper.toResponseDTO(buscarEntidadePorId(id));
    }

    @Override
    public Page<AnimalResponseDTO> listar(Pageable pageable) {
        return animalRepository.findAll(pageable).map(AnimalMapper::toResponseDTO);
    }

    @Override
    public AnimalResponseDTO atualizarParcial(Long id, AnimalPatchDTO dto) {
        Animal existente = buscarEntidadePorId(id);
        if (dto.rga() != null){
            validarRgaDuplicadoNaAtualizacao(existente, dto.rga());
        }
        AnimalMapper.applyPatch(dto, existente);
        Animal atualizado = animalRepository.save(existente);
        return AnimalMapper.toResponseDTO(atualizado);
    }


    @Override
    public void remover(Long id) {
        if(!animalRepository.existsById(id)){
            throw new IllegalArgumentException("Animal não encontrado. id=" + id);
        }
        animalRepository.deleteById(id);
    }


    @Override
    @Transactional
    public AnimalResponseDTO atualizar(Long id, AnimalUpdateDTO dto) {
        //busca animal no banco, caso não exista lança exceção
        Animal existente = buscarEntidadePorId(id);

        //valida novo RGA- lanca excesão caso duplicado
        validarRgaDuplicadoNaAtualizacao(existente, dto.rga());

        //atualizada o animal existente
        AnimalMapper.applyUpdate(dto, existente);

        //salva a atualização no repositório
        Animal atualizado = animalRepository.save(existente);
        return AnimalMapper.toResponseDTO(atualizado);
    }



    // -------------------------
    // Auxiliares / validações
    // -------------------------
    private void validarRgaDuplicadoNoCadastro(String rga) {
        if (rga != null && animalRepository.existsByRga(rga)) {
            throw new IllegalArgumentException("Já existe um animal cadastrado com o RGA informado.");
        }
    }


    // retorna entidade por id, caso não encontrada lança uma exceção
    private Animal buscarEntidadePorId(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado. id=" + id));
    }


    private void validarRgaDuplicadoNaAtualizacao(Animal existente, String rgaNovo) {
        String rgaAtual = existente.getRga();
        // se não mudou, não valida
        if (rgaNovo != null && rgaNovo.equals(rgaAtual)) {
            return;
        }
        // se mudou, verifica duplicidade
        if (rgaNovo != null && animalRepository.existsByRga(rgaNovo)) {
            throw new IllegalArgumentException("Já existe um animal cadastrado com o RGA informado.");
        }
    }






}
