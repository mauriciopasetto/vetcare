package br.com.vetcare.animal.repository;

import br.com.vetcare.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsByRga(String rga);

}

