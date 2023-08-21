package com.br.biblioteca.service;

import org.springframework.data.domain.Page;

import com.br.biblioteca.dto.GeneroDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public interface GeneroService {

	GeneroDTO findById(Long id);

	Page<GeneroDTO> findAll(
			@Min(value = 0, message = "Valor mínimo para o número de página é 0") @Max(200) Integer numeroPagina,
			@Min(value = 5, message = "No mínimo 5 registros por página") @Max(200) Integer quantidadeRegistros,
			@Size(min = 3, max = 4, message = "Os campos aceitos são somente 'desc' ou 'asc'") String ordem,
			@Size(min = 2, message = "Campo ordenação inválido") String campoOrdenacao);

	GeneroDTO create(@NonNull @Valid GeneroDTO authorPersist);

	GeneroDTO atualizar(@NonNull @Valid GeneroDTO authorPersist, Long id);

	void delete(Long id);

}
