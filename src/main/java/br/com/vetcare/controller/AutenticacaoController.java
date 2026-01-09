package br.com.vetcare.controller;

import br.com.vetcare.usuario.model.Usuario;
import br.com.vetcare.infra.security.AuthenticationDTO;
import br.com.vetcare.infra.security.DadosTokenJWT;
import br.com.vetcare.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationDTO dados){
        // Cria o DTO do Spring
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // O Manager chama o Service, que chama o Repository, que checa a senha criptografada
        var authentication = manager.authenticate(authenticationToken);

        // Se chegou aqui, a senha está correta. Geramos o token.
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}