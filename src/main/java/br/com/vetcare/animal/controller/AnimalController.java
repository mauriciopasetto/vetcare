package br.com.vetcare.animal.controller;

import br.com.vetcare.animal.dto.AnimalCreateDTO;
import br.com.vetcare.animal.dto.AnimalPatchDTO;
import br.com.vetcare.animal.dto.AnimalResponseDTO;
import br.com.vetcare.animal.dto.AnimalUpdateDTO;
import br.com.vetcare.animal.repository.AnimalRepository;
import br.com.vetcare.animal.service.AnimalService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/animais")
public class AnimalController {

    //Injeção de dependencias via construtor
    private final AnimalService animalService;

    public AnimalController(AnimalRepository animalRepository, AnimalService animalService) {
        this.animalService = animalService;
    }


    @PostMapping
    public ResponseEntity<AnimalResponseDTO> criar(@Valid @RequestBody AnimalCreateDTO animalCreateDTO) {
        return ResponseEntity.status(201).body(animalService.salvar(animalCreateDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }


    @GetMapping
    public ResponseEntity<Page<AnimalResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(animalService.listar(pageable));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> atualizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody AnimalPatchDTO animalPatchDTO
            )
    {
        return ResponseEntity.ok(animalService.atualizarParcial(id, animalPatchDTO));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        animalService.remover(id);
        return ResponseEntity.noContent().build(); //204 noContent
    }


    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AnimalUpdateDTO dto
    ) {
        return ResponseEntity.ok(animalService.atualizar(id, dto));
    }



}
