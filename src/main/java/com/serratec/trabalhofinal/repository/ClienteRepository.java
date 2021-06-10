package com.serratec.trabalhofinal.repository;

import java.util.Optional;

import com.serratec.trabalhofinal.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
<<<<<<< HEAD:src/main/java/com/trabalhofinal/demo/repository/ClienteRepository.java
	
        Optional<Cliente> findById(Integer Id);
=======
	Optional<Cliente> findById(Integer Id);
>>>>>>> 5b0348de562cbc24ebc1c6ff8d9405dad14d65ec:src/main/java/com/serratec/trabalhofinal/repository/ClienteRepository.java
}
