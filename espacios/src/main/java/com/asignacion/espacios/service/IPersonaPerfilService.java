package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.entity.PersonaPerfil;

public interface IPersonaPerfilService {

	
	
	//Repository
	List<PersonaPerfil> listarPerfilHabilitadoPorIdPerfil(boolean indHabilitado, int idPerfil);
	
}
