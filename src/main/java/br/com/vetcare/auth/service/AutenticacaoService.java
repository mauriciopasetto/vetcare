package br.com.vetcare.auth.service;

import br.com.vetcare.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por ensinar ao Spring Security como buscar um usuário no banco de dados.
 * Ele é chamado automaticamente quando o AuthenticationManager.authenticate() é executado no Controller.
 */
@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo login (email)
        // Se não encontrar, o Spring Security trata o retorno null ou lança exceção automaticamente
        return repository.findByLogin(username);
    }
}
