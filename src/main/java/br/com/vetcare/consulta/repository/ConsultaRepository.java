package br.com.vetcare.consulta.repository;

import br.com.vetcare.consulta.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Page<Consulta> findAllByAnimalId(Long animalId, Pageable pageable);
    Page<Consulta> findAllByVeterinarioId(Long veterinarioId, Pageable pageable);
    boolean existsByVeterinarioIdAndDataConsultaAndHoraConsulta(Long veterinarioId,LocalDate dataConsulta,LocalTime horaConsulta);
    boolean existsByAnimalIdAndDataConsultaAndHoraConsulta(Long animalId, LocalDate data, LocalTime hora);

    // Consultas a partir de uma data (Inclusive)
    Page<Consulta> findAllByDataConsultaGreaterThanEqual(LocalDate data, Pageable pageable);
    // Consultas em uma data específica
    Page<Consulta> findAllByDataConsulta(LocalDate data, Pageable pageable);
    // Consultas entre duas datas (Período)
    Page<Consulta> findAllByDataConsultaBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

}