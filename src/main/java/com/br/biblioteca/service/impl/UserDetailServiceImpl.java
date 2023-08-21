package com.br.biblioteca.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.biblioteca.dto.UserDetailsInfo;
import com.br.biblioteca.model.UsuarioModel;
import com.br.biblioteca.repository.UsuarioRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    public UserDetailServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> optionalUser = repository.findByEmail(username);
        return new UserDetailsInfo(optionalUser.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
    }
}
