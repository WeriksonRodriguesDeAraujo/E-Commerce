package com.trabalhofinal.demo.repository;

import java.util.Optional;

import com.trabalhofinal.demo.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
        Optional<Produto> findById(Integer Id);
}
