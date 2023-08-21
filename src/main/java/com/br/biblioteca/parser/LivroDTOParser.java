package com.br.biblioteca.parser;

import com.br.biblioteca.dto.LivroDTO;
import com.br.biblioteca.dto.LivroDTOUpdateAndCreate;
import com.br.biblioteca.model.AutorModel;
import com.br.biblioteca.model.GeneroModel;
import com.br.biblioteca.model.LivroModel;

public class LivroDTOParser {

	public static LivroDTO toDTO(LivroModel model) {
		LivroDTO dto = new LivroDTO();

		dto.setId(model.getId());
		dto.setNome(model.getNome());
		dto.setDataPublicacao(model.getDataPublicacao());
		dto.setGenero(model.getGenero().getNome());
		dto.setAutor(model.getAutor().getNome());
		return dto;
	}

	public static LivroModel toModel(LivroDTOUpdateAndCreate dto) {
		LivroModel model = new LivroModel();
		model.setId(dto.getId());
		model.setNome(dto.getNome());
		model.setGenero(GeneroModel.builder().id(dto.getGenero()).build());
		model.setAutor(AutorModel.builder().id(dto.getAutor()).build());
		model.setDataPublicacao(dto.getDataPublicacao());
		return model;
	}

}
