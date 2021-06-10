package com.serratec.trabalhofinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.trabalhofinal.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	Optional<Pedido> findById(Integer id); 
}
