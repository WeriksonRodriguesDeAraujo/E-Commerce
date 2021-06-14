package com.serratec.trabalhofinal.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.trabalhofinal.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	 Optional<Categoria> findById(Integer id);
	 
	 List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
