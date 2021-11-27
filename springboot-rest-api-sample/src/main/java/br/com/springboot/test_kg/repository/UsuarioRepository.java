package br.com.springboot.test_kg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.test_kg.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}