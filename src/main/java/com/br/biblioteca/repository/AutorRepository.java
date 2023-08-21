package com.br.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.biblioteca.model.AutorModel;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, Long>, JpaSpecificationExecutor<AutorModel> {

	Page<AutorModel> findAll(Pageable pageable);

}
