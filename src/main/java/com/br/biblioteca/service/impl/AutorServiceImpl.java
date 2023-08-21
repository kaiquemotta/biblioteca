package com.br.biblioteca.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.biblioteca.dto.AutorDTO;
import com.br.biblioteca.exceptions.ObjetoNaoEncontrado;
import com.br.biblioteca.model.AutorModel;
import com.br.biblioteca.parser.AutorDTOParser;
import com.br.biblioteca.repository.AutorRepository;
import com.br.biblioteca.service.AutorService;

import jakarta.validation.Valid;
import lombok.NonNull;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository autorRepository;

	public AutorDTO findById(Long id) {
		Optional<AutorModel> autor = autorRepository.findById(id);
		return AutorDTOParser
				.toAutorDTO(autor.orElseThrow(() -> new ObjetoNaoEncontrado("Não encontrado autor de ID " + id)));
	}

	public Page<AutorDTO> findAll(Integer numeroPagina, Integer quantidadeRegistros, String ordem,
			String campoOrdenacao) {
		Sort ordenacao = Sort.by(Sort.Direction.fromString(ordem), campoOrdenacao);
		PageRequest pageRequest = PageRequest.of(numeroPagina, quantidadeRegistros, ordenacao);

		Page<AutorModel> page = autorRepository.findAll(pageRequest);

		return page.map(AutorDTOParser::toAutorDTO);
	}

	@Override
	public AutorDTO novoAutor(@NonNull @Valid AutorDTO dto) {
		AutorModel autor = AutorDTOParser.toAutor(dto);
		autorRepository.save(autor);
		return AutorDTOParser.toAutorDTO(autor);
	}

	@Override
	public void delete(Long id) {
		AutorDTO autor = this.findById(id);
		autorRepository.delete(AutorDTOParser.toAutor(autor));
	}

	@Override
	public AutorDTO atualizar(@NonNull @Valid AutorDTO authorPersist, Long id) {
		AutorDTO dto = this.findById(id);
		AutorModel autor = AutorDTOParser.toAutor(dto);
		return AutorDTOParser.toAutorDTO(autorRepository.save(this.atualizaDadosUpdate(autor, authorPersist)));

	}

	private AutorModel atualizaDadosUpdate(AutorModel autor, @NonNull @Valid AutorDTO authorPersist) {
		autor.setNome(authorPersist.getNome());
		return autor;
	}

}
