package com.br.biblioteca.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.biblioteca.config.JwtService;
import com.br.biblioteca.dto.AuthRequestDTO;
import com.br.biblioteca.dto.AuthResponseDTO;
import com.br.biblioteca.exceptions.UsuarioNaoEncontradoException;
import com.br.biblioteca.model.UsuarioModel;
import com.br.biblioteca.repository.UsuarioRepository;


@Service
public class AuthService {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        UsuarioModel usuario = usuarioRepository.findByEmail(request.getUsername()).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("nome", usuario.getNome());

        String token = jwtService.generateToken(usuario, extraClaims);
        return new AuthResponseDTO(token);
    }
}
