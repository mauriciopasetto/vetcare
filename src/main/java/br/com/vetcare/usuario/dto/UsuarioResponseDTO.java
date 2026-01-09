package br.com.vetcare.usuario.dto;

import br.com.vetcare.auth.model.UserRole;

public record UsuarioResponseDTO(
        Long id,
        String login,
        UserRole role
) {}