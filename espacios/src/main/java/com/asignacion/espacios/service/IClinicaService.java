package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Clinica;

public interface IClinicaService {

	// NATIVO=======================================================================================================================
	Clinica buscarClincaId(Integer idClinica);

	List<Clinica> listarTodos();

	void guardarClinica(Clinica clinica);

	Clinica guardarClinicaReturn(Clinica clinica);

	// REPOSITORY===================================================================================================================
	Clinica buscarPorNombre(String nombre);

	// METODOS Y LOGICA=============================================================================================================
	Mensaje guardarValidando(Clinica clinica);

}
