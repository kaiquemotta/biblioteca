package com.br.biblioteca.parser;

import com.br.biblioteca.dto.GeneroDTO;
import com.br.biblioteca.model.GeneroModel;

public class GeneroDTOParser {

	public static GeneroDTO toDTO(GeneroModel model) {
		GeneroDTO dto = new GeneroDTO();
		dto.setId(model.getId());
		dto.setNome(model.getNome());
		return dto;
	}

	public static GeneroModel toModel(GeneroDTO dto) {
		GeneroModel model = new GeneroModel();
		model.setId(dto.getId());
		model.setNome(dto.getNome());
		return model;
	}
}
