package br.com.vetcare.tutor.repository;

import br.com.vetcare.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByRg(String rg);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
