package com.trabalhofinal.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhofinal.demo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	 Optional<Categoria> findById(Integer id); 
}
