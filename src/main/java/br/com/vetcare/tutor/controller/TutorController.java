package br.com.vetcare.tutor.controller;

import br.com.vetcare.tutor.dto.TutorCreateDTO;
import br.com.vetcare.tutor.dto.TutorPatchDTO;
import br.com.vetcare.tutor.dto.TutorResponseDTO;
import br.com.vetcare.tutor.dto.TutorUpdateDTO;
import br.com.vetcare.tutor.repository.TutorRepository;
import br.com.vetcare.tutor.service.TutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutores")
@Tag(name = "Tutor", description = "Endpoints para gerenciamento de tutores") // Anotação do Swagger
public class TutorController {

    //Injeção de dependencias via construtor
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }


    @PostMapping
    public ResponseEntity<TutorResponseDTO> criar(@Valid @RequestBody TutorCreateDTO tutorCreateDTO) {
        return ResponseEntity.status(201).body(tutorService.salvar(tutorCreateDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }


    @GetMapping
    public ResponseEntity<Page<TutorResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(tutorService.listar(pageable));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody TutorPatchDTO tutorPatchDTO
    )
    {
        return ResponseEntity.ok(tutorService.atualizarParcial(id, tutorPatchDTO));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tutorService.remover(id);
        return ResponseEntity.noContent().build(); //204 noContent
    }


    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TutorUpdateDTO dto
    ) {
        return ResponseEntity.ok(tutorService.atualizar(id, dto));
    }



}
