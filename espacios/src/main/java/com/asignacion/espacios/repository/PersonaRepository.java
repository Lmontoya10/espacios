package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	
	Persona findByIdentificacion(String identificacion);
    Persona findByEmail(String email);
	
  
	
	
}
