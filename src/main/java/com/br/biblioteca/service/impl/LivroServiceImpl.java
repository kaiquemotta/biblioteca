package com.br.biblioteca.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.biblioteca.dto.LivroDTO;
import com.br.biblioteca.dto.LivroDTOUpdateAndCreate;
import com.br.biblioteca.exceptions.ObjetoNaoEncontrado;
import com.br.biblioteca.model.AutorModel;
import com.br.biblioteca.model.GeneroModel;
import com.br.biblioteca.model.LivroModel;
import com.br.biblioteca.parser.LivroDTOParser;
import com.br.biblioteca.repository.LivroRepository;
import com.br.biblioteca.service.LivroService;

import jakarta.validation.Valid;
import lombok.NonNull;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public LivroDTO findById(Long id) {
		Optional<LivroModel> model = livroRepository.findById(id);
		return LivroDTOParser
				.toDTO(model.orElseThrow(() -> new ObjetoNaoEncontrado("Não encontrado autor de ID " + id)));
	}

	public Page<LivroDTO> findAll(Integer numeroPagina, Integer quantidadeRegistros, String ordem,
			String campoOrdenacao) {
		Sort ordenacao = Sort.by(Sort.Direction.fromString(ordem), campoOrdenacao);
		PageRequest pageRequest = PageRequest.of(numeroPagina, quantidadeRegistros, ordenacao);
		Page<LivroModel> page = livroRepository.findAll(pageRequest);
		return page.map(LivroDTOParser::toDTO);
	}

	@Override
	public LivroDTO create(@NonNull @Valid LivroDTOUpdateAndCreate dto) {
		LivroModel model = LivroDTOParser.toModel(dto);
		livroRepository.save(model);
		return LivroDTOParser.toDTO(model);
	}

	@Override
	public void delete(Long id) {
		LivroDTO model = this.findById(id);
		livroRepository.deleteById(model.getId());
	}

	@Override
	public LivroDTO atualizar(@NonNull @Valid LivroDTO dto, Long id) {
		Optional<LivroModel> model = livroRepository.findById(id);
		
		LivroModel modelUpdate = this.atualizaDadosUpdate(model.get(),dto);

		return LivroDTOParser.toDTO(livroRepository.save(modelUpdate));

	}

	private LivroModel atualizaDadosUpdate(LivroModel model, LivroDTO dto) {
		LivroModel modelUpdate = new LivroModel();
		modelUpdate.setAutor(AutorModel.builder().id(dto.getId()).build());
		modelUpdate.setGenero(GeneroModel.builder().id(dto.getId()).build());
		modelUpdate.setNome(dto.getNome());
		modelUpdate.setDataPublicacao(dto.getDataPublicacao());
		return modelUpdate;
	}

}
