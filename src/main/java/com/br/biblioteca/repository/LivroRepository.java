package com.br.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.biblioteca.model.LivroModel;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long>, JpaSpecificationExecutor<LivroModel> {

	Page<LivroModel> findAll(Pageable pageable);

}
