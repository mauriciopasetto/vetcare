package br.com.vetcare.consulta.service;

import br.com.vetcare.animal.model.Animal;
import br.com.vetcare.animal.repository.AnimalRepository;
import br.com.vetcare.consulta.dto.*;
import br.com.vetcare.consulta.mapper.ConsultaMapper;
import br.com.vetcare.consulta.model.Consulta;
import br.com.vetcare.consulta.repository.ConsultaRepository;
import br.com.vetcare.veterinario.model.Veterinario;
import br.com.vetcare.veterinario.repository.VeterinarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;


    @Override
    @Transactional
    public ConsultaResponseDTO agendar(ConsultaCreateDTO dto) {
        // Valida se o Animal existe
        Animal animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com ID: " + dto.animalId()));

        // Valida se o Veterinário existe
        Veterinario vet = veterinarioRepository.findById(dto.veterinarioId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado com ID: " + dto.veterinarioId()));

        // Verificação de conflito do VETERINÁRIO data/hora consulta
        if (consultaRepository.existsByVeterinarioIdAndDataConsultaAndHoraConsulta(
                dto.veterinarioId(), dto.dataConsulta(), dto.horaConsulta())) {
            throw new RuntimeException("O veterinário já possui consulta neste horário.");
        }

        // 3. Verificação de conflito do ANIMAL data/hora consulta
        if (consultaRepository.existsByAnimalIdAndDataConsultaAndHoraConsulta(
                dto.animalId(), dto.dataConsulta(), dto.horaConsulta())) {
            throw new RuntimeException("Este animal já possui uma consulta agendada para este mesmo horário.");
        }

        Consulta consulta = ConsultaMapper.toEntity(dto);
        consulta.setAnimal(animal);
        consulta.setVeterinario(vet);

        return ConsultaMapper.toResponseDTO(consultaRepository.save(consulta));
    }


    @Override
    @Transactional(readOnly = true)
    public ConsultaResponseDTO buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .map(ConsultaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id));
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarTodos(Pageable pageable) {
        return consultaRepository.findAll(pageable)
                .map(ConsultaMapper::toResponseDTO);
    }


    @Override
    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaUpdateDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        ConsultaMapper.applyUpdate(dto, consulta);

        return ConsultaMapper.toResponseDTO(consultaRepository.save(consulta));
    }


    @Override
    @Transactional
    public ConsultaResponseDTO atualizarParcial(Long id, ConsultaPatchDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        ConsultaMapper.applyPatch(dto, consulta);

        return ConsultaMapper.toResponseDTO(consultaRepository.save(consulta));
    }


    @Override
    @Transactional
    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarPorAnimal(Long animalId, Pageable pageable) {
        // Opcional: validar se o animal existe antes de buscar
        if (!animalRepository.existsById(animalId)) {
            throw new EntityNotFoundException("Animal não encontrado");
        }

        return consultaRepository.findAllByAnimalId(animalId, pageable)
                .map(ConsultaMapper::toResponseDTO);
    }

    @Override
    public Page<ConsultaResponseDTO> listarPorVeterinario(Long veterinarioId, Pageable pageable) {
        // Opcional: validar se o animal existe antes de buscar
        if (!veterinarioRepository.existsById(veterinarioId)) {
            throw new EntityNotFoundException("Veterinario não encontrado");
        }

        return consultaRepository.findAllByVeterinarioId(veterinarioId, pageable)
                .map(ConsultaMapper::toResponseDTO);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarApartirDe(LocalDate data, Pageable pageable) {
        return consultaRepository.findAllByDataConsultaGreaterThanEqual(data, pageable)
                .map(ConsultaMapper::toResponseDTO);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarPorData(LocalDate data, Pageable pageable) {
        return consultaRepository.findAllByDataConsulta(data, pageable)
                .map(ConsultaMapper::toResponseDTO);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarPorPeriodo(LocalDate inicio, LocalDate fim, Pageable pageable) {
        return consultaRepository.findAllByDataConsultaBetween(inicio, fim, pageable)
                .map(ConsultaMapper::toResponseDTO);
    }


}