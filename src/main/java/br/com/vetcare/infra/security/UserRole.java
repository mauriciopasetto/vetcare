package br.com.vetcare.infra.security;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    VETERINARIO("veterinario"),
    TUTOR("tutor");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}