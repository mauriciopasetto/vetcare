package br.com.vetcare.usuario.dto;

import br.com.vetcare.infra.security.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCreateDTO(
        @NotBlank @Email String login,
        @NotBlank String senha,
        @NotNull UserRole role
) {}
