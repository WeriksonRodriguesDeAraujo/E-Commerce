package com.trabalhofinal.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhofinal.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	Optional<Pedido> findById(Integer id); 
}
