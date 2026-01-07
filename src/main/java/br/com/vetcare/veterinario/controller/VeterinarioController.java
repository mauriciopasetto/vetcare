package br.com.vetcare.veterinario.controller;


import br.com.vetcare.veterinario.dto.VeterinarioCreateDTO;
import br.com.vetcare.veterinario.dto.VeterinarioPatchDTO;
import br.com.vetcare.veterinario.dto.VeterinarioResponseDTO;
import br.com.vetcare.veterinario.dto.VeterinarioUpdateDTO;
import br.com.vetcare.veterinario.service.VeterinarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/veterinarios")
@Tag(name = "Veterinario", description = "Endpoints para gerenciamento de veterinarios") // Anotação do Swagger
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }


    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> criar(@Valid @RequestBody VeterinarioCreateDTO veterinarioCreateDTO) {
        return ResponseEntity.status(201).body(veterinarioService.salvar(veterinarioCreateDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }


    @GetMapping
    public ResponseEntity<Page<VeterinarioResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(veterinarioService.listar(pageable));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> atualizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody VeterinarioPatchDTO veterinarioPatchDTO
    )
    {
        return ResponseEntity.ok(veterinarioService.atualizarParcial(id, veterinarioPatchDTO));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        veterinarioService.remover(id);
        return ResponseEntity.noContent().build(); //204 noContent
    }


    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody VeterinarioUpdateDTO dto
    ) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, dto));
    }



}
