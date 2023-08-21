package com.br.biblioteca.parser;

import com.br.biblioteca.dto.NovoUsuarioDTO;
import com.br.biblioteca.dto.UsuarioDTO;
import com.br.biblioteca.model.UsuarioModel;


public class UsuarioDTOParser {

    public static UsuarioDTO toUsuarioDTO(UsuarioModel usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setNome(usuario.getNome());
        dto.setId(usuario.getId());
        return dto;
    }

    public static NovoUsuarioDTO toNovoUsuarioDTO(UsuarioModel user) {
        NovoUsuarioDTO dto = new NovoUsuarioDTO();
        dto.setCpf(user.getCpf());
        dto.setPass(user.getPassword());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static UsuarioModel toUsuarioEntity(NovoUsuarioDTO dto) {
    	UsuarioModel entity = new UsuarioModel();
        entity.setCpf(dto.getCpf());
        entity.setPassword(dto.getPass());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
