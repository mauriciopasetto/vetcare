package br.com.vetcare.usuario.service;

import br.com.vetcare.usuario.dto.UsuarioCreateDTO;
import br.com.vetcare.usuario.dto.UsuarioResponseDTO;
import br.com.vetcare.usuario.mapper.UsuarioMapper;
import br.com.vetcare.usuario.model.Usuario;
import br.com.vetcare.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioCreateDTO dto) {
        // 1. Criptografa a senha recebida no DTO
        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        // 2. Cria o usuário com a senha já criptografada
        Usuario usuario = new Usuario(dto.login(), senhaCriptografada, dto.role());

        // 3. Salva e retorna
        return UsuarioMapper.toResponseDTO(repository.save(usuario));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
    }
}