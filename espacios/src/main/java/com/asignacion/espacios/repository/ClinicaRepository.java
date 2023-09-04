package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.entity.Clinica;



public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
	Clinica findByNombre(String nombre);
}
