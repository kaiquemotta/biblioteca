package com.br.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.biblioteca.dto.NovoUsuarioDTO;
import com.br.biblioteca.service.UsuarioService;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService servico;
	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		NovoUsuarioDTO dto = new NovoUsuarioDTO();
		dto.setPass("12345678");
		dto.setNome("kaique");
		dto.setCpf("123.456.789-10");
		dto.setEmail("kaique.motta@hotmail.com");
		servico.salvar(dto);
	}

}
