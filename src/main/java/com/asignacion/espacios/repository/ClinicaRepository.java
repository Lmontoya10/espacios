package com.asignacion.espacios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.model.entity.Clinica;



public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
	
	Clinica findByMaeGrupoListaNombre_IdListaAndMaeGrupoListaCae_IdListaAndMaeGrupoListaDia_IdListaAndMaeGrupoListaHorario_IdListaAndIndHabilitado (int idListaNombre, int idListaCae, int idListaDia, int idListaHorario, boolean indHabilitado);
	
	
	
	
}
