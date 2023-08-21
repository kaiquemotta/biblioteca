package com.br.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LIVRO")
@SequenceGenerator(name = "livro_seq", sequenceName = "livro_seq", initialValue = 7, allocationSize = 1)
public class LivroModel {

	@Id
    @GeneratedValue(generator = "livro_seq")
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private AutorModel autor;
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private GeneroModel genero;
	private String dataPublicacao;
}
