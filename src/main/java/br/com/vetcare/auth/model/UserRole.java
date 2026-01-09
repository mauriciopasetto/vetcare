package br.com.vetcare.auth.model;

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