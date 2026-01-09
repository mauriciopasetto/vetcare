package br.com.vetcare.usuario.dto;

import br.com.vetcare.infra.security.UserRole;

public record UsuarioResponseDTO(
        Long id,
        String login,
        UserRole role
) {}