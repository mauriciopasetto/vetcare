package br.com.vetcare.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (necessário pois não usamos sessão do navegador)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define autenticação Stateless (sem sessão)
                .authorizeHttpRequests(req -> {
                    // #Libera o login para todos (senão ninguém entra)
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();

                    // req.requestMatchers(HttpMethod.POST, "/api/veterinarios/**").hasRole("ADMIN");
                    // req.requestMatchers(HttpMethod.PUT, "/api/veterinarios/**").hasRole("ADMIN");
                    // req.requestMatchers(HttpMethod.DELETE, "/api/veterinarios/**").hasRole("ADMIN");
                    // #Libera GET, PUT, DELETE, PATCH, etc. quando não especifica HttpMethod
                    // req.requestMatchers("/usuarios").hasRole("ADMIN");
                    // #Consultas: Apenas VET e ADMIN podem ver detalhes ou alterar
                    req.requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN", "VET");
                    req.requestMatchers("/api/consultas/**").hasAnyRole("ADMIN", "VET");
                    req.requestMatchers("/api/tutores/**").hasAnyRole("ADMIN", "VET");
                    req.requestMatchers("/api/animais/**").hasAnyRole("ADMIN", "VET");
                    req.requestMatchers("/api/veterinario/**").hasAnyRole("ADMIN", "VET");

                    // #Bloqueia qualquer outra rota que não tenha sido liberada acima
                    req.anyRequest().authenticated();
                })
                // #Adiciona nosso filtro de Token ANTES do filtro padrão do Spring
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Este Bean corrige o erro "No beans of 'AuthenticationManager' type found".
     * Ele expõe o gerenciador de autenticação do Spring para ser injetado no Controller.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Define o algoritmo de hash de senha.
     * O BCrypt é o padrão da indústria atualmente.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}