package br.com.vetcare.consulta.controller;

import br.com.vetcare.consulta.dto.*;
import br.com.vetcare.consulta.service.ConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Consulta", description = "Endpoints para gerenciamento de consultas") // Anotação do Swagger
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendar(@RequestBody @Valid ConsultaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.agendar(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }


    @GetMapping
    public ResponseEntity<Page<ConsultaResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarTodos(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaUpdateDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody ConsultaPatchDTO dto) {
        return ResponseEntity.ok(consultaService.atualizarParcial(id, dto));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        consultaService.excluir(id);
    }


    @GetMapping("/animal/{animalId}")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorAnimal(
            @PathVariable Long animalId,
            Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarPorAnimal(animalId, pageable));
    }


    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorVeterinario(
            @PathVariable Long veterinarioId,
            Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarPorVeterinario(veterinarioId, pageable));
    }


    @GetMapping("/filtro/apos")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarApartirDe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarApartirDe(data, pageable));
    }


    @GetMapping("/filtro/data")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarPorData(data, pageable));
    }


    @GetMapping("/filtro/periodo")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarPorPeriodo(inicio, fim, pageable));
    }

}