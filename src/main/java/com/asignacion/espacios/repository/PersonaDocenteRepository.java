package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.model.entity.PersonaDocente;

public interface PersonaDocenteRepository extends JpaRepository<PersonaDocente, Integer> {

}
