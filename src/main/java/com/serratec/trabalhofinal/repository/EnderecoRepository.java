package com.serratec.trabalhofinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.trabalhofinal.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	Optional<Endereco> findById(Integer id);

}
