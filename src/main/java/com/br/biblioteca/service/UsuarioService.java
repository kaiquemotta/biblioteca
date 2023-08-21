package com.br.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.biblioteca.dto.NovoUsuarioDTO;
import com.br.biblioteca.dto.UsuarioDTO;
import com.br.biblioteca.exceptions.UsuarioNaoEncontradoException;
import com.br.biblioteca.model.UsuarioModel;
import com.br.biblioteca.parser.UsuarioDTOParser;
import com.br.biblioteca.repository.UsuarioRepository;

@Component
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
	}

	public UsuarioDTO salvar(NovoUsuarioDTO user) {
		user.setPass(encoder.encode(user.getPass()));
		return UsuarioDTOParser.toUsuarioDTO(usuarioRepository.save(UsuarioDTOParser.toUsuarioEntity(user)));
	}

	public List<UsuarioDTO> findAll() {
		return usuarioRepository.findAll().stream().map(UsuarioDTOParser::toUsuarioDTO).collect(Collectors.toList());
	}

	public UsuarioDTO findById(Long id) {
		UsuarioModel user = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário de ID " + id + " não encontrado."));

		return UsuarioDTOParser.toUsuarioDTO(user);
	}

	public UsuarioDTO atualizar(NovoUsuarioDTO usuario, Long id) {
		findById(id);
		UsuarioModel usuarioEntity = UsuarioDTOParser.toUsuarioEntity(usuario);
		usuarioEntity.setId(id);

		return UsuarioDTOParser.toUsuarioDTO(usuarioRepository.save(usuarioEntity));

	}

	public void deletar(Long id) {
		findById(id);
		usuarioRepository.deleteById(id);
	}
}
