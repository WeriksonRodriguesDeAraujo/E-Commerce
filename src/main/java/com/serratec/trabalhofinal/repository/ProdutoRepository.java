package com.serratec.trabalhofinal.repository;

import java.util.Optional;

import com.serratec.trabalhofinal.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	Optional<Produto> findById(Integer Id);
}
