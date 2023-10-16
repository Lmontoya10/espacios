package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.model.entity.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
	Modulo findByCodigo(String codigo);
}
