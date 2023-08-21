package com.br.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.biblioteca.model.AutorModel;
import com.br.biblioteca.model.GeneroModel;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroModel, Long>, JpaSpecificationExecutor<GeneroModel> {

	Page<GeneroModel> findAll(Pageable pageable);

}
