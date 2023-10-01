package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.entity.Puesto;

public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
	
}