package com.br.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GENERO")
@SequenceGenerator(name = "genero_seq", sequenceName = "genero_seq", initialValue = 7, allocationSize = 1)
public class GeneroModel {

	@Id
    @GeneratedValue(generator = "genero_seq")
	private Long id;
	private String nome;

}
