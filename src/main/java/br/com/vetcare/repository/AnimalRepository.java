package br.com.vetcare.repository;

import br.com.vetcare.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsByRga(String rga);

}

