package com.br.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

	private Long id;
	private String nome;
	private String autor;
	private String genero;
	private String dataPublicacao;

}
