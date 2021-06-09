package com.serratec.trabalhofinal.repository;

import java.util.Optional;

import com.serratec.trabalhofinal.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Optional<Cliente> findById(Integer Id);
}
