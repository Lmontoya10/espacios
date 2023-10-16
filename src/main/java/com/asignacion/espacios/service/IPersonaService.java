package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Persona;

public interface IPersonaService {

	//NATIVO=======================================================================================================================
	Persona buscarPersonaId (Integer idPersona);
	List<Persona> listarTodos ();
	void guardarPersona (Persona persona);
	Persona guardarPersonaReturn (Persona persona);
	
	//REPOSITORY===================================================================================================================
	Persona buscarPorDocumento (String documento);
	Persona buscarPorEmail (String email);
	
	
	
	//METODOS Y LOGICA=============================================================================================================
	Mensaje guardarValidando (Persona persona, Boolean perfilEstudiante, Boolean perfilDocente, Boolean perfilAuxiliar, Boolean perfilAdministrador);
	
}
