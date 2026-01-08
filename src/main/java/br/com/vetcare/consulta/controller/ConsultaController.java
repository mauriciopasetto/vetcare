package br.com.vetcare.consulta.controller;

import br.com.vetcare.consulta.dto.*;
import br.com.vetcare.consulta.service.ConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Consulta", description = "Endpoints para gerenciamento de consultas") // Anotação do Swagger
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendar(@RequestBody @Valid ConsultaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agendar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaUpdateDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody ConsultaPatchDTO dto) {
        return ResponseEntity.ok(service.atualizarParcial(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        service.excluir(id);
    }
}