package com.asignacion.espacios.service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.PersonaDocente;

public interface IPersonaDocenteService {

	
	//NATIVO
	PersonaDocente buscarPorId(int idPersona);
	void guardar(PersonaDocente personaDocente);
	void eliminarPorId(int idPersona);
	
	//REPOSITORY
	
	//METODOS Y LÓGICA
	Mensaje guardarEliminarValidando(PersonaDocente personaDocente, Boolean perfilDocente);
	
	
}
