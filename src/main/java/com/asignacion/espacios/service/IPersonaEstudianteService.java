package com.asignacion.espacios.service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.PersonaEstudiante;

public interface IPersonaEstudianteService {

	//NATIVO
	PersonaEstudiante buscarPorId(int idPersona);
	void guardar(PersonaEstudiante personaEstudiante);
	void eliminarPorId(int idPersona);
	
	//REPOSITORY
	
	//METODOS Y LÓGICA
	Mensaje guardarEliminarValidando(PersonaEstudiante personaEstudiante, Boolean perfilEstudiante);
	
}
