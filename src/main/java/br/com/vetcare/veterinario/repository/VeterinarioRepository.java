package br.com.vetcare.veterinario.repository;

import br.com.vetcare.veterinario.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByCrmv(String crmv);
    boolean existsByEmail(String email);

}

