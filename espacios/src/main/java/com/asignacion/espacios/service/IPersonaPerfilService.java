package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.entity.PersonaPerfil;

public interface IPersonaPerfilService {

	//NATIVO
	void guardarLista (List<PersonaPerfil> listaPersonaPerfil);
	
	
	//Repository
	List<PersonaPerfil> listarPerfilHabilitadoPorIdPerfil(boolean indHabilitado, int idPerfil);
	List<PersonaPerfil> listaPerfilesPersonaPorIdPersona (int idPersona);
}
