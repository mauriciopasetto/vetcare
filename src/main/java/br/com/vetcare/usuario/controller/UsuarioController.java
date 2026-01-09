package br.com.vetcare.usuario.controller;

import br.com.vetcare.usuario.dto.UsuarioCreateDTO;
import br.com.vetcare.usuario.dto.UsuarioResponseDTO;
import br.com.vetcare.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioCreateDTO dto, UriComponentsBuilder uriBuilder) {
        UsuarioResponseDTO usuarioCriado = service.cadastrar(dto);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioCriado.id()).toUri();

        return ResponseEntity.created(uri).body(usuarioCriado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}