package com.br.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.biblioteca.dto.GeneroDTO;
import com.br.biblioteca.dto.LivroDTO;
import com.br.biblioteca.dto.LivroDTOUpdateAndCreate;
import com.br.biblioteca.service.GeneroService;
import com.br.biblioteca.service.LivroService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

@RestController
@Validated
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(livroService.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<LivroDTO>> findAll(
			@RequestParam(value = "numeroPagina", required = false, defaultValue = "0") @Min(value = 0, message = "Valor mínimo para o número de página é 0") @Max(200) Integer numeroPagina,
			@RequestParam(value = "quantidadeRegistros", required = false, defaultValue = "150") @Min(value = 5, message = "No mínimo 5 registros por página") @Max(200) Integer quantidadeRegistros,
			@RequestParam(value = "ordem", required = false, defaultValue = "desc") @Size(min = 3, max = 4, message = "Os campos aceitos são somente 'desc' ou 'asc'") String ordem,
			@RequestParam(value = "campoOrdenacao", required = false, defaultValue = "nome") @Size(min = 2, message = "Campo ordenação inválido") String campoOrdenacao) {
		return ResponseEntity.ok(livroService.findAll(numeroPagina, quantidadeRegistros, ordem, campoOrdenacao));
	}

	@PostMapping
	public ResponseEntity<?> criar(@NonNull @Valid @RequestBody LivroDTOUpdateAndCreate dto) {
		LivroDTO dtoCreated = livroService.create(dto);
		return new ResponseEntity<String>("OK", HttpStatus.CREATED);
	}

	@PutMapping(path = ("/{id}"))
	public ResponseEntity<?> atualizar(@NonNull @Valid @RequestBody LivroDTO dto, @PathVariable("id") Long id) {
		LivroDTO dtoUpdated = livroService.atualizar(dto, id);
		return new ResponseEntity<String>("OK", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = ("/{id}"))
	public ResponseEntity<?> deleteAutor(@PathVariable("id") Long id) {
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
