package br.com.vetcare.usuario.service;

import br.com.vetcare.usuario.dto.UsuarioCreateDTO;
import br.com.vetcare.usuario.dto.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO cadastrar(UsuarioCreateDTO dto);
    List<UsuarioResponseDTO> listarTodos();
}