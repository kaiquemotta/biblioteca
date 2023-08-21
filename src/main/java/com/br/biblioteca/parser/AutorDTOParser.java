package com.br.biblioteca.parser;

import com.br.biblioteca.dto.AutorDTO;
import com.br.biblioteca.model.AutorModel;

public class AutorDTOParser {

	public static AutorDTO toAutorDTO(AutorModel autor) {
		AutorDTO dto = new AutorDTO();
		dto.setId(autor.getId());
		dto.setNome(autor.getNome());
		return dto;
	}
	
	
	public static AutorModel toAutor(AutorDTO dto) {
		AutorModel autorModel = new AutorModel();
		autorModel.setId(dto.getId());
		autorModel.setNome(dto.getNome());
		return autorModel;
	}
}
