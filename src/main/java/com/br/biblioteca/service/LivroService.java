package com.br.biblioteca.service;

import org.springframework.data.domain.Page;

import com.br.biblioteca.dto.LivroDTO;
import com.br.biblioteca.dto.LivroDTOUpdateAndCreate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public interface LivroService {

	LivroDTO findById(Long id);

	Page<LivroDTO> findAll(
			@Min(value = 0, message = "Valor mínimo para o número de página é 0") @Max(200) Integer numeroPagina,
			@Min(value = 5, message = "No mínimo 5 registros por página") @Max(200) Integer quantidadeRegistros,
			@Size(min = 3, max = 4, message = "Os campos aceitos são somente 'desc' ou 'asc'") String ordem,
			@Size(min = 2, message = "Campo ordenação inválido") String campoOrdenacao);

	LivroDTO create(@NonNull @Valid LivroDTOUpdateAndCreate dto);

	LivroDTO atualizar(@NonNull @Valid LivroDTO dto, Long id);

	void delete(Long id);

}
