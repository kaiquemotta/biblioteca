package com.br.biblioteca.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.biblioteca.dto.GeneroDTO;
import com.br.biblioteca.exceptions.ObjetoNaoEncontrado;
import com.br.biblioteca.model.GeneroModel;
import com.br.biblioteca.parser.GeneroDTOParser;
import com.br.biblioteca.repository.GeneroRepository;
import com.br.biblioteca.service.GeneroService;

import jakarta.validation.Valid;
import lombok.NonNull;

@Service
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	public GeneroDTO findById(Long id) {
		Optional<GeneroModel> autor = generoRepository.findById(id);
		return GeneroDTOParser
				.toDTO(autor.orElseThrow(() -> new ObjetoNaoEncontrado("Não encontrado autor de ID " + id)));
	}

	public Page<GeneroDTO> findAll(Integer numeroPagina, Integer quantidadeRegistros, String ordem,
			String campoOrdenacao) {
		Sort ordenacao = Sort.by(Sort.Direction.fromString(ordem), campoOrdenacao);
		PageRequest pageRequest = PageRequest.of(numeroPagina, quantidadeRegistros, ordenacao);

		Page<GeneroModel> page = generoRepository.findAll(pageRequest);

		return page.map(GeneroDTOParser::toDTO);
	}

	@Override
	public GeneroDTO create(@NonNull @Valid GeneroDTO dto) {
		GeneroModel model = GeneroDTOParser.toModel(dto);
		generoRepository.save(model);
		return GeneroDTOParser.toDTO(model);
	}

	@Override
	public void delete(Long id) {
		GeneroDTO model = this.findById(id);
		generoRepository.delete(GeneroDTOParser.toModel(model));
	}

	@Override
	public GeneroDTO atualizar(@NonNull @Valid GeneroDTO dto, Long id) {
		GeneroDTO dtoFind = this.findById(id);
		GeneroModel model = GeneroDTOParser.toModel(dtoFind);
		return GeneroDTOParser.toDTO(generoRepository.save(this.atualizaDadosUpdate(model, dto)));

	}

	private GeneroModel atualizaDadosUpdate(GeneroModel model, @NonNull @Valid GeneroDTO dto) {
		model.setNome(dto.getNome());
		return model;
	}

}
