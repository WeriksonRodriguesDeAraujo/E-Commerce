package com.trabalhofinal.demo.repository;

import java.util.Optional;

import com.trabalhofinal.demo.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        Optional<Cliente> findById(Integer Id);
}
