package br.com.vetcare.usuario.mapper;

import br.com.vetcare.usuario.dto.UsuarioCreateDTO;
import br.com.vetcare.usuario.dto.UsuarioResponseDTO;
import br.com.vetcare.usuario.model.Usuario;

public final class UsuarioMapper {

    // Convertemos para Entity SEM a senha aqui, pois a senha precisa ser HASHED no Service
    public static Usuario toEntity(UsuarioCreateDTO dto) {
        return new Usuario(dto.login(), dto.senha(), dto.role());
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getUsername(), // login
                entity.getRole()
        );
    }
}
