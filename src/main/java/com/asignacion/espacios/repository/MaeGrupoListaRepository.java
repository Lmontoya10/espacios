package com.asignacion.espacios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asignacion.espacios.entity.MaeGrupoLista;

public interface MaeGrupoListaRepository extends JpaRepository<MaeGrupoLista, Integer> {

	List<MaeGrupoLista> findByMaegrupo_IdGrupoAndIndHabilitadoOrderByOrdenListaAsc(int idGrupo, boolean indHabilitado);
	
	
	
}
